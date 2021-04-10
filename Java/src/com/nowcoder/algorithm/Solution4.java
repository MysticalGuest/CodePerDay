package com.nowcoder.algorithm;

/*
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，
 * 最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 * 
 * 图的最短路径？
 * 迪杰斯特拉算法和弗洛伊德算法
 * Dijkstra：从一个顶点 其余各顶点的最短路径O(n^2)
 * Floyd：求每对顶点间距离O(n^3)
 * 这两种都不需要，第一次没做出来，想法很简单：就是顺着走下来，发现行不通，很多种情况。
 * */
public class Solution4 {
	
	/**
     * 
     * @param matrix int整型二维数组 the matrix
     * @return int整型
     */
	/*
    public int minPathSum (int[][] matrix) {
    	int row = matrix.length; // 行
    	if (row==0) {
			return 0;
		}
    	int col=matrix[row-1].length;
    	if (col==0) {
			return 0;
		}
    	int current_index[] = {0, 0}; // 记录当前停留位置
//    	int current_xindex=0, current_yindex=0;
        int sum=matrix[current_index[0]][current_index[1]]; // 起始位置出发点
        int next=101; // 由于题目中矩阵 要求数值范[1, 100]，这里用next存储下一次路径长，这里设置最大值，后面好比较
//    	int sum=matrix[current_xindex][current_yindex], next;
        int [] direction[] = {
        		{0, -1}, // 左
        		{-1, 0}, // 上
        		{0, 1}, // 右
        		{1, 0} // 下
        		}; // 4个方向
//        java语言声明数组的时候不能指定其长度（元素的个数）
//        boolean flag[4]; 非法
//        boolean flag[] = {false, false, false, false};
        boolean flag[] = {true, true, true, true};
        
        while(!(current_index[0]==row-1 || current_index[1]==col-1)) {
        	if (current_index[0]==0&&current_index[1]==0) { // 只有2个方向，右，下，其余方向置false
//				flag[2] = true; flag[3] = true;
        		flag[0]=false; flag[1]=false;
			}
        	if (current_index[0]==row-1&&current_index[1]==0) { // 只有2个方向，右，上
//				flag[0] = true; flag[3] = true;
        		flag[1]=false; flag[2]=false;
			}
        	if (current_index[0]==0&&current_index[1]==col-1) { // 只有2个方向，左，下
//				flag[1] = true; flag[2] = true;
        		flag[0]=false; flag[3]=false;
			}
        	if (current_index[0]==row-1&&current_index[1]==0) { // 只有2个方向，左，上
//				flag[0] = true; flag[1] = true;
        		flag[2]=false; flag[3]=false;
			}
        	if (current_index[0]==0) { // 有3个方向，左，下，右，剩下一个方向置false
//				flag[0]=true; flag[2]=true; flag[3]=true;
        		flag[1]=false;
			}
        	if (current_index[0]==row-1) { // 有3个方向，上，下，左
//				flag[0]=true; flag[1]=true; flag[2]=true;
        		flag[3]=false;
			}
        	if (current_index[1]==0) { // 有3个方向，左，下，右
//				flag[1]=true; flag[2]=true; flag[3]=true;
				flag[0]=false;
			}
        	if (current_index[1]==col-1) { // 有3个方向，左，上，右
//				flag[0]=true; flag[1]=true; flag[3]=true;
        		flag[2]=false;
			}
        	int x, y, c_i0=current_index[0], c_i1=current_index[1]; // c_i0和c_i1用来记录当前位置，因为之后要变的
        	int current_next;
        	int forbidden=0; // 总存在一个不能返回的方向
        	for (int i = 0; i < direction.length; i++) { // 往4个方向走并比较
				if(flag[i]) { // 先判断方向是否可走
					x=direction[i][0]; // 第一个方向往哪走
					y=direction[i][1]; // 第二个方向往哪走
					System.out.println("x: "+x+"  y: "+y);
					current_next=matrix[c_i0+x][c_i1+y];
					System.out.println("current_next: "+current_next);
					if(next>current_next) {
						next = current_next;
						current_index[0]=c_i0+x;
						current_index[1]=c_i1+y;
						System.err.println("current_index[0]: "+current_index[0]+"  current_index[1]: "+current_index[1]);
//						forbidden=i; // 从左来，不能往右回，从上往下来，不能往上回
						System.out.println("i: "+i);
						switch (i) {
						case 0:forbidden=2;break;
						case 1:forbidden=3;break;
						case 2:forbidden=0;break;
						case 3:forbidden=1;break;
						default:
							break;
						}
						System.out.println("forbidden: "+forbidden);
					}
				}
			}
        	sum+=next;
        	
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("  sum   : "+sum);
        	// 还有一个问题，并不是所有的都有4个方向可以走，因为它不能原路返回，所以用的之前的标记
        	for (int i = 0; i < flag.length; i++) {
        		if(i!=forbidden) {
        			flag[i]=true;
        		}
			}// 标志至原来状态
        }
        return sum;
    }
	*/
	
	/*
	 * 在题解中找到一个思路很完美，思路简单，代码量少
	 * 但我认为这个代码有bug，比如打印出这个copy矩阵
	 * 1 4 9 18 
	 * 9 5 8 12 
	 * 14 5 11 12 
	 * 22 13 15 12 
	 * copy[2][0]这里是14，应该为10？
	 * */
	public int minPathSum (int[][] matrix) {
		int row = matrix.length; // 行
    	if (row==0) {
			return 0;
		}
    	int col=matrix[0].length;
    	if (col==0) {
			return 0;
		}
		int [][] copy = new int[row][col];
		copy[0][0]=matrix[0][0];
		for (int i = 1; i < copy.length; i++) {
			copy[i][0]=copy[i-1][0]+matrix[i][0];
		}
		for (int i = 1; i < copy[0].length; i++) {
			copy[0][i]=copy[0][i-1]+matrix[0][i];
		}
		for (int i = 1; i < copy.length; i++) {
			for (int j = 1; j < copy[0].length; j++) {
				copy[i][j]=Math.min(copy[i][j-1], copy[i-1][j])+matrix[i][j];
			}
		}
		printArray(copy);
		return copy[row-1][col-1];
	}
	
	
	// Print the two-dimensional array.
	public static void printArray(int [][]a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
    
    public static void main(String[] args) {
    	// 输入：[[1,3,5,9],[8,1,3,4],[5,0,6,1],[8,8,4,0]]
    	// 返回值：12
    	// 二维数组的定义
    	int [][] arr = new int[][]{{1,3,5,9}, {8,1,3,4}, {5,0,6,1}, {8,8,4,0}};
    	printArray(arr);
    	
    	Solution4 s4 = new Solution4();
    	System.out.println("Min path sum: "+s4.minPathSum(arr));
    	
    	int [] arr1[] ={{1,2},{2,3,4},{3,4,5,6}};
//    	arr1[0][2]=9;  java.lang.ArrayIndexOutOfBoundsException: 说明数组静态，空间一旦被声明就固定
    	printArray(arr1);
    	
    	int [][]arr2=new int[3][];
    	arr2[0]=new int[3];  //3
    	arr2[1]=new int[]{1,2,3,4};  //3
    	arr2[2]=new int[2];  //2
    	printArray(arr2);
    	
    	int []arr3[]=new int[3][4];
    	printArray(arr3);
    	
//    	int []arr4[];
//    	printArray(arr4); The local variable arr4 may not have been initialized
	}

}
