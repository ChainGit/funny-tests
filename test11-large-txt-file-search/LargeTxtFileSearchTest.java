package com.chain.test.day09;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.chain.utils.RandomStringUtils;

/**
 * 大文件查找
 * 
 * @author chain
 *
 */
public class LargeTxtFileSearchTest {

	private static final String path = "C:\\Temps\\large.txt";

	// 总共的数据项数
	private static final int RECORDS_NUM = 1000_0000;
	// 要查找的数据项
	private static final int SEARCHS_NUM = 100;

	private static Map<Integer, String> searchs = null;

	public static void main(String[] args) {
		System.out.println("====== HASH SEARCH ======");
		System.out.println();

		System.out.println("START MAKING");
		long start = System.currentTimeMillis();
		make();
		long end = System.currentTimeMillis();
		System.out.println("MAKING COST " + (end - start));

		System.out.println();

		// 快速构建Hash表
		System.out.println("FAST MODE");
		Map<Integer, List<Integer>> resA = fun(true);

		System.out.println();

		// 慢速构建Hash表
		System.out.println("SLOW MODE");
		Map<Integer, List<Integer>> resB = fun(false);

		System.out.println();

		// 比较结果是否一致
		System.out.println(resA.equals(resB));
	}

	private static Map<Integer, List<Integer>> fun(boolean fast) {
		LargeTxtFileSearch fs = new LargeTxtFileSearch(path);

		System.out.println("START BUILDING");
		long start = System.currentTimeMillis();
		// 两种构建方式
		fs.build(fast);
		long end = System.currentTimeMillis();
		System.out.println("BUILDING COST " + (end - start));

		System.out.println();

		Map<Integer, List<Integer>> map = new HashMap<>();

		System.out.println("START FINDING");
		start = System.currentTimeMillis();
		for (Map.Entry<Integer, String> e : searchs.entrySet()) {
			int key = e.getKey();
			String value = e.getValue();
			long step = System.currentTimeMillis();
			List<Integer> finds = fs.find(value);
			long mark = System.currentTimeMillis();
			System.out.println(key + " " + value);
			if (finds != null) {
				map.put(key, finds);
				int size = finds.size();
				System.out.println("FIND: " + size);
				// 最多打印5个
				int show = size > 5 ? 5 : size;
				System.out.print("EXAMPLE: ");
				for (int i = 0; i < show; i++)
					System.out.print(finds.get(i) + " ");
				System.out.println();
			} else {
				System.out.println("FIND: " + null);
			}
			System.out.println("FINDING STEP COST " + (mark - step));
		}
		end = System.currentTimeMillis();
		System.out.println("FINDING TOTAL COST " + (end - start));

		try {
			fs.close();
		} catch (IOException e1) {
		}

		return map;
	}

	// 生成大文本文件
	private static void make() {
		File largeTxt = new File(path);
		if (largeTxt.exists())
			largeTxt.delete();
		Random rand = new Random();
		searchs = new HashMap<>();
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(largeTxt));
			String base = "http://url.cn/";
			byte[] nlBytes = System.getProperty("line.separator", "\n").getBytes();
			byte[] baseBytes = base.getBytes();
			int randStrLen = 15;
			for (int i = 0; i < RECORDS_NUM; i++) {
				String url = RandomStringUtils.generateString(rand.nextInt() & randStrLen);
				bos.write(baseBytes);
				bos.write(url.getBytes());
				// 最后一行必须要有换行符
				bos.write(nlBytes);
				// 随机选择一些URL加入到searchs中去
				if (searchs.size() < SEARCHS_NUM) {
					int t = rand.nextInt() % 100;
					if (t > 30 && t < 50)
						// 行号从1开始
						searchs.put(i + 1, base + url);
				}
			}
			bos.flush();
			System.out.println("make has writen lines " + RECORDS_NUM);
			System.out.println("search nums " + searchs.size());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
	}

}
