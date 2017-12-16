package com.chain.test.day05;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

import com.chain.utils.FileVerifyUtils;

/**
 * 检查文件是否一致
 * 
 * @author chain
 *
 */
public class Check {

	private Properties prop;

	private String sharePath;
	private String shareFileName;

	private int readersNum;

	public Check() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream("bin/com/chain/test/day05/test.properties"));
		sharePath = prop.getProperty("file.path");
		shareFileName = prop.getProperty("file.name");
		readersNum = Integer.valueOf(prop.getProperty("readers.num"));
	}

	@Test
	public void check() throws Exception {
		String share = FileVerifyUtils.verify(FileVerifyUtils.MD5, sharePath + File.separator + shareFileName);
		String[] readers = new String[readersNum];
		for (int i = 0; i < readersNum; i++)
			readers[i] = FileVerifyUtils.verify(FileVerifyUtils.MD5,
					sharePath + File.separator + "reader_" + i + ".txt");

		System.out.println(shareFileName + ": " + share);

		for (int i = 0; i < readersNum; i++)
			System.out.println("reader_" + i + ".txt: " + readers[i]);

		boolean ok = true;
		for (int i = 0; i < readersNum; i++)
			if (!share.equals(readers[i])) {
				ok = false;
				break;
			}

		System.out.println(ok);
	}
}
