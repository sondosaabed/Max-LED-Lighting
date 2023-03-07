package Application;

/*
 * In this class, the optimal solution for maximum number of leds powered by power source is given uisng dynamic programing approach 
 * specifically Longest common subsequent algorithm, and the sequnece that gives the optimal solution is traced
 */

 public class LongestCommonSubSequence {
	//feilds
	private int power;//Number of power sources
	private int[] powers; //Array of the batteries
	private int[] leds; //Array of the LEDs as read from file
	private char[][] b;//Array of DP table for arrows
	private int[][] c;//Array of DP table for lengths
	private String lcs ="";//The maximum powered LEDs 
	private String lcss[];//The exact LEDs powered

	public LongestCommonSubSequence(int []y, int []x){
		setPowers(y);
		setLeds(x);
		setPower(x.length);
		
		longestCommonSubArrayLenghth(y, x);
		print(getB(),getLeds(),getPower()-1,getPower()-1);
		setLcss(getLcs().split(" "));
	}

	private int longestCommonSubArrayLenghth(int[]y,int[]x) {
		//This method returns the length of the longest common sub array
		int n = y.length;
		int m = x.length;

		c = new int[n][m];
		b = new char[n+1][m+1];
		
		for(int i=0;i<n;i++)
			c[0][i]=0;
		
		for(int i=0;i<m;i++)
			c[i][0]=0;
		
		for(int i=1; i<m; i++)
			for(int j=1;j<n;j++)
				if(x[i-1]==y[j-1]) {
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]='\\';
				}else if(c[i][j-1]>c[i-1][j]) {
					c[i][j]=c[i][j-1];
					b[i][j]='<';
				}else {
					c[i][j]=c[i-1][j];
					b[i][j]='^';
				}
		return c[n-1][m-1];
	}
	
	private void print(char [][]b, int [] leds, int i, int j) {
		//In this method the longest common sub- array is created
		if(i!=0 && j!=0) {
			if(b[i][j]=='\\') {
				print(b,leds,i-1,j-1);
				lcs=lcs+leds[i-1]+" ";
			}else if(b[i][j]=='<') {
				print(b,leds,i,j-1);
			}else if(b[i][j]=='^'){
				print(b,leds,i-1,j);
			}
		}
	}
	/*
	 * Getters & setters
	 */
	public String[] getLcss() {
		return lcss;
	}

	public void setLcss(String lcss[]) {
		this.lcss = lcss;
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int[] getPowers() {
		return powers;
	}

	public void setPowers(int[] powers) {
		this.powers = powers;
	}

	public int[] getLeds() {
		return leds;
	}

	public void setLeds(int[] leds) {
		this.leds = leds;
	}

	public char[][] getB() {
		return b;
	}

	public void setB(char[][] b) {
		this.b = b;
	}

	public int[][] getC() {
		return c;
	}

	public void setC(int[][] c) {
		this.c = c;
	}

	public String getLcs() {
		return lcs;
	}

	public void setLcs(String lcs) {
		this.lcs = lcs;
	}
}