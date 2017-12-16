package com.chain.test.day05;

/**
 * 读者写者的规则的抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractReaderWriterMethod {

	/**
	 * 读者读文件
	 * 
	 * @param reader
	 * @throws Exception
	 */
	public abstract void read(AbstractReader reader) throws Exception;

	/**
	 * 写者写文件
	 * 
	 * @param writer
	 * @throws Exception
	 */
	public abstract void write(AbstractWriter writer) throws Exception;

}
