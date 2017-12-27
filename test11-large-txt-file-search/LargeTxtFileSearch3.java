package com.chain.test02;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大型文件查找（仅适合文本格式的行式存储数据）
 * 
 * 单层hash表
 * 
 * 这个方法不一定准确（是不严谨的），只是为了加快查找
 * 
 * @author chain
 *
 */
public class LargeTxtFileSearch3 extends AbstractLargeTxtFileSearch {

	// 文本文件的路径
	private String path;

	// Map<Hash值，数据所在行的信息>
	private Map<Integer, List<LineInfo>> store;

	private static final int BASE = 1023;

	// 数据所在行的信息
	private class LineInfo {
		// 数据所在行号
		private int lineNumber;
		// 该行数据的完整hash值
		private int hashValue;

		public LineInfo(int lineNumber, int hashValue) {
			this.lineNumber = lineNumber;
			this.hashValue = hashValue;
		}

		public boolean isSame(LineInfo o) {
			return o.hashValue == hashValue;
		}

	}

	public LargeTxtFileSearch3(String path) {
		this.path = path;
	}

	/**
	 * 构建hash表
	 */
	public void build(boolean fast) {
		if (path == null)
			throw new RuntimeException("path can not be null");
		build();
	}

	private void build() {
		store = new HashMap<>();
		LineNumberReader bufr = null;
		try {
			// 行号从1开始
			bufr = new LineNumberReader(new FileReader(path));
			String lineStr = null;
			List<LineInfo> lineInfos = null;
			while ((lineStr = bufr.readLine()) != null) {
				// 方便find查找
				int realHashValue = hash(lineStr);
				int hashCode = Math.abs(realHashValue) & BASE;
				lineInfos = store.get(hashCode);
				if (lineInfos == null) {
					lineInfos = new ArrayList<>();
					store.put(hashCode, lineInfos);
				}
				// 顺序添加，更进一步的意思是按照出现的顺序添加，能简化查找操作
				lineInfos.add(new LineInfo(bufr.getLineNumber(), realHashValue));
			}
			System.out.println("build slow-mode has read lines " + bufr.getLineNumber());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (bufr != null)
				try {
					bufr.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
	}

	private int hash(String buf) {
		if (buf == null)
			throw new RuntimeException("string is null");
		int h = 0;
		byte[] val = buf.getBytes();
		for (int i = 0; i < val.length; i++)
			// 采用31可以使得hash之后更加均匀，减少冲突，即每个常数算出的哈希值冲突数都小于7个
			// 2、3、17、31、101都可以，这里使用31，不过101效果最佳（分布最均匀）
			// 因为数字31有一个很好的特性，即乘法运算可以被移位和减法运算取代，来获取更好的性能：31 * i == (i << 5) - i
			h = 31 * h + val[i];
		return h;
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
			throw new RuntimeException("has not been built yet");
		int searchHashValue = hash(search);
		int hashCode = Math.abs(searchHashValue) & BASE;
		List<LineInfo> lineInfos = store.get(hashCode);
		if (lineInfos == null)
			return null;
		LineInfo searchLineInfo = new LineInfo(-1, searchHashValue);
		List<Integer> lines = new ArrayList<>();
		for (LineInfo line : lineInfos) {
			// hash不一样，内容肯定不一样，能加快判断的速度
			if (!searchLineInfo.isSame(line))
				continue;
			// 行号从1开始
			lines.add(line.lineNumber);
		}
		return lines.size() > 0 ? lines : null;
	}

	/**
	 * 关闭资源
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
	}
}
