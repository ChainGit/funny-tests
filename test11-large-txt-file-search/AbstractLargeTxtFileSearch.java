package com.chain.test02;

import java.io.IOException;
import java.util.List;

/**
 * 大文件查找抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractLargeTxtFileSearch {

	/**
	 * 关闭资源
	 * 
	 * @throws IOException
	 */
	public abstract void close() throws IOException;

	/**
	 * 查找
	 * 
	 * @param search
	 * @return
	 */
	public abstract List<Integer> find(String search);

	/**
	 * 构建hash表
	 */
	public abstract void build(boolean fast);

}
