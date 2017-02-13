package com.gsc.math;

public class Caculate {

	public static void main(String[] args) {
		
		test1();
//		test2();
//		test3();
		
	}
	
	public static void test3() {
		double original = 1;
		double source = original*original;
		double precision = 0.001;
		double result = 0;
		while(true) {
			System.out.println("source=" + source + ",precision=" + precision);
			result = sqrt2(source, precision);
			System.out.println("sqrt2 caculate result:" + result);
			if(result>(original + precision) || result<(original - precision)) {
				System.out.println("计算结果超过允许误差值");
				break;
			}
			original ++;
			source = original*original;
		}
	}
	
	public static void test2() {
		double source = 9;
		double precision = 0.001;
		System.out.println("sqrt.sqrt:" + sqrt(source, precision));
		System.out.println("sqrt1.sqrt:" + sqrt1(source, precision));
		System.out.println("sqrt2.sqrt:" + sqrt2(source, precision));
		System.out.println("Math.sqrt:" + Math.sqrt(source));
	}
	
	public static void test1() {
		double source = 888888888;
		double precision = 0.0000000001;
		System.out.println("sqrt  calculate result:" + sqrt(source, precision));
		System.out.println("sqrt1 calculate result:" + sqrt1(source, precision));
		System.out.println("sqrt2 calculate result:" + sqrt2(source, precision));
		System.out.println("math  calculate result:" + Math.sqrt(source));
	}
	
	/**
	 * 求开平方 优化，二分法
	 * 该方法效率明显比前两个方法快的多
	 * @param source 被开方数，大于等于0
	 * @param deviation 误差范围
	 * @return
	 */
	public static double sqrt2(double source,double deviation) {
		
		if(source < 0 || deviation <0) {
			throw new RuntimeException("don't transmit negtive");
		}
		long count = 1;//统计循环执行次数
		double result = 0;
		
		double head = source;
		double tail = 0;
		while(true) {
			count++;
			if(((head+tail)/2) * ((head+tail)/2) < source) {
				tail = (head+tail)/2;
			} else {
				head = (head+tail)/2;
			}
			result = (head+tail)/2;
			if((result + deviation)*(result + deviation) >= source &&
					(result - deviation)*(result - deviation) <= source) {
				break;
			}
		}
		System.out.println("sqrt2 total count:" + count);
		return result;
	}
	
	/**
	 * 求开平方 优化，步长调整
	 * @param source 被开方数，大于等于0
	 * @param deviation 误差范围
	 * @return
	 */
	public static double sqrt1(double source,double deviation) {
		
		if(source < 0 || deviation <0) {
			throw new RuntimeException("don't transmit negtive");
		}
		long count = 1;//统计循环执行次数
		double result = 0;
		int stepI = 2;
		double stepD = deviation;
		long stepCount = 1;
		while ((result + 1) * (result + 1) < source) {
			count++;
			stepCount++;
			if(stepCount%3==0){  //加快结果累计，调整步长
				if((result + stepI) * (result + stepI) < source) {
					result += stepI;
					stepI++;
					continue;
				} else {
					stepI--;
				}
			}
			result++;
		}
		stepCount = 0;
		while ((result + deviation) * (result + deviation) < source ) {
			count++;
			stepCount++;
			if(stepCount%3==0){  //加快结果累计，调整步长
				if((result + stepD) * (result + stepD) < source) {
					result += stepD;
					stepD+=deviation;
					continue;
				} else {
					stepD-=deviation;
				}
			}
			result += deviation;
		}
		System.out.println("sqrt1 total count :" + count);
		return result;
	}
	
	/**
	 * 求开平方
	 * @param source 被开平方数，大于等于0
	 * @param deviation 误差范围
	 * @return
	 */
	public static double sqrt(double source,double deviation) {
		
		if(source < 0 || deviation <0) {
			throw new RuntimeException("don't transmit negtive");
		}
		long count = 1;//统计循环执行次数
		double result = 0;
		while ((result + 1) * (result + 1) < source) {
			count++;
			result++;
		}
		while ((result + deviation) * (result + deviation) < source ) {
			count++;
			result += deviation;
		}
		System.out.println("sqrt total count:" + count);
		return result;
	}
}
