package com.nowcoder.algorithm;

/*
 * 动态规划
 * 空间换时间，建表
 * 
 * 已知一个背包最多能容纳物体的体积为V
 * 现有n个物品
 * 第i个物品的体积为v_i
 * 第i个物品的重量为w_iw 
 * 求当前背包最多能装多大重量的物品
 * 示例1
 * 输入：10,2,[[1,3],[10,4]]
 * 输出（返回值）：4
 * 第一个物品的体积为1，重量为3，第二个物品的体积为10，重量为4。只取第二个物品可以达到最优方案，取物重量为4 
 * */
public class Knapsack {
	
	/**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack (int V, int n, int[][] vw) {
    	int row = vw.length;
    	if(row==0||n==0)
    		return 0;
//    	int col = vw[0].length;
    	if (vw[0].length<2) {
			return 0;
		}
    	
    	int [][] bag = new int[n+1][V+1]; // bag[i][j]表示背包容量为j时，前i个物品最大重量
    	/*
    	 * 那么我们可以将dp[0][0...W]初始化为0，表示将前0个物品（即没有物品）装入书包的最大价值为0。那么当 i > 0 时dp[i][j]有两种情况：
    	 *   1.不装入第i件物品，即dp[i−1][j]；
    	 *   2.装入第i件物品（前提是能装下），即dp[i−1][j−w[i]] + v[i]。
    	 * 即状态转移方程为
    	 *   dp[i][j] = max(dp[i−1][j], dp[i−1][j−w[i]]+v[i]) // j >= w[i]
    	 * */
    	for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < V+1; j++) {
				if (vw[i-1][0]>j) { // 装不下
					bag[i][j]=bag[i-1][j];
				}
				else { // 装得下，又分两种情况
					int remain = j-vw[i-1][0]; // 预留的位置
					bag[i][j]=Math.max(bag[i-1][remain]+vw[i-1][1], bag[i-1][j]);
				}
			}
		}
    	
//    	for (int i = 0; i < n+1; i++) {
//			for (int j = 0; j < V+1; j++) {
//				System.err.print(bag[i][j]+" ");
//			}
//			System.out.println("s");
//		}
    	
    	return bag[n][V];
    }
    
    public static void main(String[] args) {
		int [][] arr = {{1, 3},{10, 4}};
		int [][] arr1 = {};
		int [][] arr2 = {{1, 3},{10, 4},{10, 2}, {11, 5}, {9, 9}};
		int [][] arr3 = {{1, 3},{1, 4},{10, 2}, {1, 5}, {9, 9}};
		
		/*
		 * 用例：
		 * 200,200,[[183,153],[28,164],[163,145],[53,155],[18,37],[56,14],[159,126],[123,154],
		 * [116,189],[139,174],[171,97],[83,119],[111,188],[195,89],[10,135],[173,7],[64,15],
		 * [108,100],[36,192],[134,179],[137,105],[84,52],[91,96],[127,49],[79,128],[157,24],
		 * [57,104],[140,17],[117,6],[174,122],[104,20],[94,73],[103,17],[19,170],[71,107],
		 * [114,122],[32,99],[56,99],[20,42],[56,88],[76,59],[114,28],[93,72],[101,86],
		 * [198,50],[94,40],[30,99],[9,24],[148,182],[136,158],[22,130],[178,199],[190,67],
		 * [117,114],[82,81],[79,89],[163,101],[121,178],[129,129],[110,78],[4,111],[154,129],
		 * [5,165],[30,100],[63,167],[171,200],[32,5],[61,28],[149,79],[123,40],[45,143],[51,42],
		 * [76,174],[195,121],[43,9],[70,9],[126,77],[163,95],[150,153],[60,173],[24,51],[118,87],
		 * [182,29],[196,95],[164,73],[65,78],[109,3],[190,172],[135,158],[96,91],[149,162],
		 * [37,103],[44,133],[169,96],[176,143],[60,186],[159,114],[166,28],[14,105],[102,57],
		 * [35,144],[48,180],[138,149],[165,143],[76,94],[55,6],[189,84],[29,151],[150,86],
		 * [59,44],[34,96],[13,189],[12,92],[190,87],[41,82],[92,42],[114,117],[79,18],[165,78],
		 * [42,83],[115,117],[80,139],[141,109],[51,114],[19,144],[129,173],[38,146],[96,196],
		 * [7,154],[164,80],[39,55],[166,177],[32,111],[143,151],[52,133],[173,64],[21,92],[85,17],
		 * [148,23],[170,192],[78,171],[84,66],[67,112],[173,128],[153,59],[72,7],[17,128],[51,200],
		 * [176,142],[127,157],[128,67],[37,21],[40,177],[123,186],[50,153],[104,185],[164,200],
		 * [100,194],[33,151],[35,41],[72,32],[75,59],[13,85],[164,109],[39,50],[64,34],[154,14],
		 * [35,131],[69,127],[125,76],[87,172],[197,133],[102,150],[96,150],[80,169],[26,126],
		 * [101,55],[37,46],[36,55],[176,113],[70,140],[193,199],[192,184],[158,170],[125,155],
		 * [25,9],[99,31],[122,139],[28,174],[129,78],[16,181],[188,49],[65,42],[197,94],[191,45],
		 * [88,188],[165,11],[91,124],[100,65],[191,18]]
		 * */
		
		Knapsack s6 = new Knapsack();
		System.out.println(s6.knapsack(10, 2, arr));
		System.out.println(s6.knapsack(10, 0, arr1));
		System.out.println(s6.knapsack(10, 5, arr2));
		System.out.println(s6.knapsack(10, 5, arr3));
	}

}
