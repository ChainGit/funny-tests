package com.chain.test02;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大型文件查找（仅适合文本格式的行式存储数据）
 * 
 * 单层hash表
 * 
 * @author chain
 *
 */
public class LargeTxtFileSearch extends AbstractLargeTxtFileSearch{

	// 文本文件的路径
	private String path;

	// Map<Hash值，数据所在行的信息>
	private Map<Integer, List<LineInfo>> store;

	private static final int BASE = 1023;

	private RandomAccessFile file;

	// 数据所在行的信息
	private class LineInfo {
		// 数据所在行号
		private int lineNumber;
		// 该行的开头在文件中的位置
		private long position;
		// 该行数据的完整hash值
		private int hashValue;

		public LineInfo(int lineNumber, long position, int hashValue) {
			this.lineNumber = lineNumber;
			this.position = position;
			this.hashValue = hashValue;
		}

	}

	public LargeTxtFileSearch(String path) {
		this.path = path;
	}

	/**
	 * 构建hash表
	 */
	public void build(boolean fast) {
		if (file != null)
			throw new RuntimeException("has built already");

		if (fast)
			build1();
		else
			build0();
	}

	// 快速
	private void build1() {
		store = new HashMap<>();
		try {
			file = new RandomAccessFile(path, "r");
			// 一次读取64M
			int bufLen = 1024 * 1024 * 64;
			byte[] buf = new byte[bufLen];
			// 注意使用long
			long times = 0;
			int readLen = -1;
			// 行号从1开始
			int lineNumber = 1;
			// 回退的位数
			long back = 0;
			while ((readLen = file.read(buf)) != -1) {
				times++;
				int cursor = 0;
				while (cursor < readLen) {
					int start = cursor;
					int offset = 0;
					int left = readLen - start;
					while (offset < left) {
						// 数据文件中的每一行数据的末尾必须要有'\n'
						if (buf[start + offset] == '\n')
							break;
						offset++;
						cursor++;
					}
					if (offset == left) {
						back += left;
						break;
					} else {
						if (offset > 0 && buf[start + offset - 1] == '\n')
							offset--;
						if (offset > 0 && buf[start + offset - 1] == '\r')
							offset--;
						String lineStr = new String(buf, start, offset);
						// 方便find查找
						int realHashValue = lineStr.hashCode();
						// 与方式取模，速度更快，前提是BASE必须是2^n-1
						int hashCode = Math.abs(realHashValue) & BASE;
						List<LineInfo> lineInfos = store.get(hashCode);
						if (lineInfos == null) {
							lineInfos = new ArrayList<>();
							store.put(hashCode, lineInfos);
						}
						// 顺序添加，更进一步的意思是按照出现的顺序添加，能简化查找操作
						lineInfos.add(new LineInfo(lineNumber++, (times - 1) * bufLen - back + start, realHashValue));
					}
					cursor++;
				}
				file.seek(times * bufLen - back);
			}
			System.out.println("build fast-mode has read lines " + (lineNumber - 1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 慢速
	private void build0() {
		store = new HashMap<>();
		try {
			file = new RandomAccessFile(path, "r");
			// 行号从1开始
			int lineNumber = 1;
			long cursor = 0;
			String lineStr = null;
			List<LineInfo> lineInfos = null;
			while ((lineStr = file.readLine()) != null) {
				// 方便find查找
				int realHashValue = lineStr.hashCode();
				int hashCode = Math.abs(realHashValue) & BASE;
				lineInfos = store.get(hashCode);
				if (lineInfos == null) {
					lineInfos = new ArrayList<>();
					store.put(hashCode, lineInfos);
				}
				// 顺序添加，更进一步的意思是按照出现的顺序添加，能简化查找操作
				lineInfos.add(new LineInfo(lineNumber++, cursor, realHashValue));
				cursor = file.getFilePointer();
			}
			System.out.println("build slow-mode has read lines " + (lineNumber - 1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找
	 * 
	 * @param search
	 * @return
	 */
	public List<Integer> find(String search) {
		if (search == null)
			throw new RuntimeException("string is null");
		if (store == null)
			throw new RuntimeException("has not built yet");
		int searchHashValue = search.hashCode();
		int hashCode = Math.abs(searchHashValue) & BASE;
		List<LineInfo> lineInfos = store.get(hashCode);
		if (lineInfos == null)
			return null;
		List<Integer> lines = new ArrayList<>();
		try {
			String lineStr = null;
			for (LineInfo line : lineInfos) {
				// hash不一样，内容肯定不一样，能加快判断的速度
				if (searchHashValue != line.hashValue)
					continue;
				file.seek(line.position);
				lineStr = file.readLine();
				// 进一步判断内容
				if (search.equals(lineStr))
					// 行号从1开始
					lines.add(line.lineNumber);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return lines.size() > 0 ? lines : null;
	}

	/**
	 * 关闭资源
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		file.close();
	}
}
