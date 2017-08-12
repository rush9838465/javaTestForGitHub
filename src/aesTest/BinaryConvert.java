package aesTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 从ATE1.1就开始提供这个类，方法比较老，1.2开始已经不怎么使用该类中得方法了<br>
 * 暂作保留的类，不再做大幅度修改和完善<br>
 * 字节，整形数，类直接相互转换，以及字节数组的合并等方法
 * @author ate
 *
 */
public class BinaryConvert{
	/**
	 * Byte[] MD5
	 * @param bytes
	 * @return
	 */
	public static byte[] getMD5(byte[] bytes) {
  	  byte[] tmp = null;
  	  try {
  	   MessageDigest digest = MessageDigest.getInstance("MD5");
  	   digest.update(bytes);
  	   tmp = digest.digest();
  	  } catch (NoSuchAlgorithmException e) {
  	   e.printStackTrace();
  	  }
  	  return tmp;
  	 }
	/**
	 * 10进制字符串变16进制
	 * @return string
	 */
		public static String stringToHexString(String s) {  
	        String str = "";  
	        for (int i = 0; i < s.length(); i++) {  
	            int ch = (int) s.charAt(i);  
	            String s4 = Integer.toHexString(ch);  
	            str = str + s4;  
	        }  
	        return str;  
	    }
		/**
		 * byte[] 转成16进制字符串
		 * @return string
		 */
		public static String bytesToHex(byte[] src){   
	        if (src == null || src.length <= 0) {   
	            return null;   
	        } 

	        StringBuilder stringBuilder = new StringBuilder("");         
	        for (int i = 0; i < src.length; i++) {   
	            // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
	            String str = Integer.toHexString(src[i] & 0xff); 
	            if (str.length() < 2) { // 不足两位要补0
	                stringBuilder.append(0);   
	            }   
	            str+=" ";
	            stringBuilder.append(str);   
	        }   
	        return stringBuilder.toString();   
	    }
		
		/**
		 * byte[] 转成16进制字符串
		 * @return string
		 */
		public static String bytesToHex2(byte[] src){   
	        if (src == null || src.length <= 0) {   
	            return null;   
	        } 

	        StringBuilder stringBuilder = new StringBuilder("");         
	        for (int i = 0; i < src.length; i++) {   
	            // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
	            String str = Integer.toHexString(src[i] & 0xff); 
	            if (str.length() < 2) { // 不足两位要补0
	                stringBuilder.append(0);   
	            }   
	            stringBuilder.append(str);   
	        }   
	        return stringBuilder.toString();   
	    }
		/**
		 * 16进制偶数字符串转byte[]
		 * @return byte[]
		 */
		 public static byte[] HexString2Bytes(String src){ 
	    	 
		    	byte[] tmp = src.getBytes(); 
		    	 byte[] ret = new byte[tmp.length/2];
		    	for(int i=0; i<tmp.length/2; i++){ 
		    	ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]); 
		    	} 
		    	return ret; 
		    	}
		 public static byte uniteBytes(byte src0, byte src1) { 
		    	byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue(); 
		    	_b0 = (byte)(_b0 << 4); 
		    	byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue(); 
		    	byte ret = (byte)(_b0 ^ _b1); 
		    	return ret; 
		    	}
		
			
		 /**
			 * 替换bit位字节为1

			 */
			public  int bitToTrue( byte[] source , int start , int pos ){
				
				if( pos >= ( ( source.length - start ) * 8 ) ) return 1;
				
				source[ start + pos / 8 ] |= ( 0x80 >> ( pos % 8 ) );
				
				return 0;
				
			}

			public int byteToInt (byte[] data,int offset,int len){  
				 int result = data[offset] & 0xff;  
				 for(int i=0;i<len;i++){  
				    result = (result << 8) | (data[offset + i] & 0xff);  
				 }  
				 return result;  
				}  
			

				public static String randomHexString(int len)  {
					try {
						StringBuffer result = new StringBuffer();
						for(int i=0;i<len;i++) {
							result.append(Integer.toHexString(new Random().nextInt(16)));
						}
						return result.toString().toUpperCase();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					}
					return null;
					} 
		 
		 
	/**
	 * 合并两个字节数组并返回
	 * 合并时不会应为'\0'而舍弃后面部分，比如：
	 * byte[] bs1=new byte[]{'1','2','3','\0','1'},bs2=new byte[]{'2','3','\n'};
	 * 合并后结果{'1','2','3','\0','1','2','3','\n'}
	 * 字符串输出结果：123123
	 * @param value1
	 * @param value2
	 * @return 返回合并以后的字节数组
	 * @throws IOException
	 */
	public static byte[] toBytes(byte[] value1,byte[] value2) throws IOException{
		ByteArrayOutputStream arrayout=new ByteArrayOutputStream();
		arrayout.write(value1);
		arrayout.write(value2);
		byte[] bytes = arrayout.toByteArray();
		arrayout.close();
		return bytes;
	}
	
	/**
	 * 截取某段byte数组,需要注意的是，该方法的开始位置（start）和长度（length）都允许负数。
	 * 且length允许超出原数组长度，允许0（返回null）
	 * 如果start为负数则表示离数组末尾的长度（比如数组【1,2,3】—— 0表示1，-1表示3，-5表示2）
	 * 如果length为负数表示逆序截取（比如【1,2,3】，0，-2——》【1,3】）
	 * 如果长度超出将自动填充'\0'（比如【1,2,3】，0,5——》【1,2,3,\0,\0】）
	 * @param bytes 原数组，如果为null则返回null
	 * @param start 开始截取位置
	 * @param length 截取长度
	 * @return 截取的数组
	 */
	public static byte[] toBytes(byte[] bytes,int start,int length){
		return toBytes(bytes,start,length,(byte) '\0');
	}
	
	/**
	 * 截取字节数组中的一部分并返回，
	 * 支持倒序返回
	 * @param bytes 源字节数组
	 * @param start 指定截取的开始位置，0为源字节数组开始位置，负数表示左移，可以超过源数组的最大长度
	 * @param length 指定截取的长度，如果超过最大长度会使用:fill字节填充。负数表示倒序截取多少个字节
	 * @param fill 截取长度超过源数组长度时填充的字节
	 * @return 截取后的字节，如果源字节数组为空或长度为0则返回空
	 */
	public static byte[] toBytes(byte[] bytes,int start,int length,byte fill){
		//可截取判断
		if(bytes==null||length==0) return null;
		
		boolean reverse=(length<0);
		//确定截取的起始位置
		if(start>=0) start=Math.abs(start)%bytes.length;
		else start=bytes.length-Math.abs(start)%bytes.length;
		
		length=Math.abs(length);
		//截取的字节数组
		byte[] temp=new byte[length];
		//将源数组正序或倒序复制到temp数组中
		if(reverse){
			//倒序复制时如果开始复制位置（start）到源字符数组的开始位置（0）处的总字节数超过要复制的长度（length）时将要复制的字节数设为要复制的长度
			int size=((start+1)<length?(start+1):length);
			//复制start到0这一段
			reverseCopy(temp,bytes,start,size);
			//复制结束位置到start这一段
			if(size<length){
				reverseCopy(temp,size,bytes,bytes.length-1,bytes.length<length?(bytes.length-size):(length-size));
			}
		}else{
			//正序复制时如果开始复制位置（start）到源字符数组的结束位置处的总字节数超过要复制的长度（length）时将要复制的字节数设为要复制的长度
			int size=(bytes.length-start)<length?(bytes.length-start):length;
			//复制start到结束位置这一段
			copy(temp,0,bytes,start,size);
			//复制0到start这一段
			if(size<length){
				copy(temp,size,length-size,bytes,0,start);
			}
		}
		//填充过多的位置
		if(length>bytes.length){
			for(int i=bytes.length;i<length;i++) temp[i]=fill;
		}
		
		return temp;
	}
	
	/**
	 * 将value2从0位置开始复制可以复制的最大长度到value1的0位置开始
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @return
	 */
	public static int copy(byte[] value1,byte[] value2){
		return copy(value1,value2,0);
	}
	
	/**
	 * 将value2从start位置开始复制可以复制的最大长度到value1的0位置开始
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @param start 源数组开始复制位置
	 * @return
	 */
	public static int copy(byte[] value1,byte[] value2,int start){
		return copy(value1,value2,start,value2.length-start);
	}
	
	/**
	 * 将value2从start位置开始复制可以复制的最大长度到value1的0位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到结尾的长度以及length这三个数值的最小值
	 * copy(byte[] value1,int start1,byte[] value2,int start2,int length)的调用，start1取0
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @param start 源数组开始复制位置
	 * @param length 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int copy(byte[] value1,byte[] value2,int start,int length){
		return copy(value1,0,value2,start,length);
	}
	
	/**
	 * 将value2从start2位置开始复制可以复制的最大长度到value1的start1位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到结尾的长度以及length这三个数值的最小值
	 * copy(byte[] value1,int start1,int length1,byte[] value2,int start2,int length2)的调用，length1取源数组开始复制位置到结尾的长度
	 * @param value1 目的数组
	 * @param start1 目的数组覆盖开始位置
	 * @param value2 源数组
	 * @param start2 源数组开始复制位置
	 * @param length 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int copy(byte[] value1,int start1,byte[] value2,int start2,int length){
		return copy(value1, start1, value1.length-start1, value2, start2, length);
	}
	
	/**
	 * 将value2从start2位置开始复制可以复制的最大长度到value1的start1位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到结尾的长度以及length1和length2这四个数值的最小值
	 * @param value1 目的数组
	 * @param start1 目的数组覆盖开始位置
	 * @param length1 目的数组需要覆盖的长度
	 * @param value2 源数组
	 * @param start2 源数组开始复制位置
	 * @param length2 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int copy(byte[] value1,int start1,int length1,byte[] value2,int start2,int length2){
		int length=(length1<length2?length1:length2);
		length1=value1.length-start1;
		length2=value2.length-start2;
		length=(length<length1?length:length1);
		length=(length<length2?length:length2);
		for(int i=0;i<length;i++){
			value1[i+start1]=value2[i+start2];
		}
		return length;
	}
	
	/**
	 * 将value2从0位置开始倒序复制可以复制的最大长度到value1的0位置开始
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @return
	 */
	public static int reverseCopy(byte[] value1,byte[] value2){
		return reverseCopy(value1,value2,0);
	}
	
	/**
	 * 将value2从start位置开始倒序复制可以复制的最大长度到value1的0位置开始
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @param start 源数组开始复制位置
	 * @return
	 */
	public static int reverseCopy(byte[] value1,byte[] value2,int start){
		return reverseCopy(value1,value2,start,value2.length-start);
	}
	
	/**
	 * 将value2从start位置开始复制可以复制的最大长度到value1的0位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到开始位置的长度以及length这三个数值的最小值
	 * copy(byte[] value1,int start1,byte[] value2,int start2,int length)的调用，start1取0
	 * @param value1 目的数组
	 * @param value2 源数组
	 * @param start 源数组开始复制位置
	 * @param length 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int reverseCopy(byte[] value1,byte[] value2,int start,int length){
		return reverseCopy(value1,0,value2,start,length);
	}
	
	/**
	 * 将value2从start2位置开始倒序复制可以复制的最大长度到value1的start1位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到开始位置的长度以及length这三个数值的最小值
	 * copy(byte[] value1,int start1,int length1,byte[] value2,int start2,int length2)的调用，length1取源数组开始复制位置到开始的长度
	 * @param value1 目的数组
	 * @param start1 目的数组覆盖开始位置
	 * @param value2 源数组
	 * @param start2 源数组开始复制位置
	 * @param length 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int reverseCopy(byte[] value1,int start1,byte[] value2,int start2,int length){
		return reverseCopy(value1, start1, value1.length-start1, value2, start2, length);
	}
	
	/**
	 * 将value2从start2位置开始倒序复制可以复制的最大长度到value1的start1位置开始
	 * 可以复制的最大长度为源数组和目的数组的开始复制位置到结尾的长度以及length1和length2这四个数值的最小值
	 * @param value1 目的数组
	 * @param start1 目的数组覆盖开始位置
	 * @param length1 目的数组需要覆盖的长度
	 * @param value2 源数组
	 * @param start2 源数组开始复制位置
	 * @param length2 源数组可以被复制的长度
	 * @return 真实复制到目的数组中的字节数
	 */
	public static int reverseCopy(byte[] value1,int start1,int length1,byte[] value2,int start2,int length2){
		int length=(length1<length2?length1:length2);
		length1=value1.length-start1;
		length2=start2+1;
		length=(length<length1?length:length1);
		length=(length<length2?length:length2);
		for(int i=0;i<length;i++){
			value1[i+start1]=value2[start2-i];
		}
		return length;
	}
	
	/**
	 * 将类序列化成字节数组，该类必须实现Serializable接口
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] toBytes(Object object) throws IOException {
		ByteArrayOutputStream arrayout=new ByteArrayOutputStream();
		ObjectOutputStream out=new ObjectOutputStream(arrayout);
		out.writeObject(object);
		byte[] bytes = arrayout.toByteArray();
		arrayout.close();
		out.close();
		return bytes;
	}
	
	/**
	 * 将被序列化的类的字节数组反序列化成类，该类必须实现Serializable接口
	 * @param bytes 被序列化数组
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		return toObject(bytes, 0, bytes.length);
	}
	
	/**
	 * 将被序列化的类的字节数组反序列化成类，该类必须实现Serializable接口
	 * @param bytes 被序列化得数组
	 * @param start 被序列化数组开始位置
	 * @param length 被序列化数组长度
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object toObject(byte[] bytes,int start,int length) throws IOException, ClassNotFoundException {
		ByteArrayInputStream arrayin = new ByteArrayInputStream(bytes,start,length); 
		ObjectInputStream in = new ObjectInputStream(arrayin);
		Object object=in.readObject();
		in.close();
		arrayin.close();
		return object;
	}
	
	/**
	 * 将字节数组转换成整形，从0位置开始的后四位字节，包括0位
	 * @param bytes
	 * @return
	 */
	public static int toInt(byte[] bytes){
		return toInt(bytes,0);
	}
	
	/**
	 * 将字节数组转换成整形，从start位置开始的后四位字节，包括start位
	 * @param bytes
	 * @param start
	 * @return
	 */
	public static int toInt(byte[] bytes,int start){
		int size=bytes.length-start;
		size=size<4?size:4;
		byte[] temp=new byte[4];
		int i=0;
		for(;i<4-size;i++) temp[i]=0;
		for(;i<4;i++){
			temp[i]=bytes[i+start];
		}
		return ((temp[0]&0xff)<<24)+((temp[1]&0xff)<<16)+((temp[2]&0xff)<<8)+(temp[3]&0xff);
	} 
	
	/**
	 * 整形数到字节数组，32位整形数转换成4位字节数组
	 * @param num
	 * @return
	 */
	public static byte[] toBytes(int num){
		byte[] result = new byte[4];
		toBytes(num,result,0);
		return result;
	}
	
	/**
	 * 整形数到字节数组，32位整形数转换成4位字节数组，存储在bytes数组中，从position位置开始的后4为，包括（position）
	 * @param num
	 * @param bytes
	 * @param position
	 */
	public static void toBytes(int num,byte[] bytes,int position){
		bytes[position] = (byte)(num >>> 24);
		bytes[position+1] = (byte)(num >>> 16);
		bytes[position+2] = (byte)(num >>> 8);
		bytes[position+3] = (byte)(num);
	}
	
	/**
	 * 整形数到字节数组，32位整形数转换成4位字节数组，存储在bytes数组中，从0位置开始的后4为，包括（position）
	 * @param num
	 * @param bytes
	 */
	public static void toBytes(int num,byte[] bytes){
		toBytes(num,bytes,0);
	}
}
