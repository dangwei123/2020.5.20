给定两个长度相等的，由小写字母组成的字符串S1和S2，定义S1和S2的距离为两个字符串有多少个位置上的字母不相等。
现在牛牛可以选定两个字母X1和X2，将S1中的所有字母X1均替换成X2。（X1和X2可以相同）
牛牛希望知道执行一次替换之后，两个字符串的距离最少为多少。

public class Solution {
    /**
     * 计算最少的距离
     * @param S1 string字符串 第一个字符串
     * @param S2 string字符串 第二个字符串
     * @return int整型
     */
    public int GetMinDistance (String S1, String S2) {
       int[][] arr=new int[26][26];
       int n=S1.length();
        for(int i=0;i<n;i++){
            arr[S1.charAt(i)-'a'][S2.charAt(i)-'a']++;
        }
        int pre=0;
        int count=0;
        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                if(i==j){
                    pre+=arr[i][i];
                }else{
                    count=Math.max(count,arr[i][j]-arr[i][i]);
                }
            }
        }
        return n-pre-count;
    }
}


众所周知，牛妹非常喜欢打游戏，在阳光明媚的一天，她在玩一个叫做打怪兽的游戏。
也许您已经知道游戏“打怪物”。如果您不知道，没关系，让我现在告诉您，森林里有那么多怪物，您就是攻击怪物并保护村庄的英雄。
为了简化问题，我们把怪物排成一行，怪物身上有很多攻击点，你有一把剑，只能切掉攻击点，当切掉一个攻击点时，怪物会分裂成两个在段中，您必须消耗与怪物一样多的能量。您的任务是将怪物切割成不包含任何攻击点。例如，您面对一个怪物，该怪物的长度为20，并且有四个攻击点在其主体上：2 5 10 18.您可以这样切割：
1.切开第一点，您使用的能量为2 + 18 = 20；
2.切第二点，您使用的能量为3 + 15 = 18；
3.切第三点，您使用的能量为5 + 10 = 15；
4.切第四点，您使用的能量是8 + 2 = 10；
您使用的总能量为：20 + 18 + 15 + 10 = 63;
但您可以采用另一种策略：
1.切第二点，您使用的能量是5 + 15 = 20；
2.切开第一点，您使用的能量为2 + 3 = 5；
3.切第三点，您使用的能量是5 + 10 = 15；
4.切第四个点，您使用的能量为8 + 2 = 10；
您使用的能量为：20 + 5 + 15 + 10 = 50;
因此您有最佳的策略来最小化需要消耗的能量。
那么问题来了，牛妹面对给定攻击点和长度的怪兽，到底最后可以用最少多少的能量打倒怪兽呢？


public class Solution {
    /**
     * 
     * @param monsterLength int整型 怪兽长度
     * @param monsterPoint int整型一维数组 怪兽攻击点位置
     * @return int整型
     */
    public int attackMonster (int monsterLength, int[] monsterPoint) {
        // write code here
        int n=monsterPoint.length;
        int[] arr=new int[n+2];
        Arrays.sort(monsterPoint);
        for(int i=0;i<n;i++){
            arr[i+1]=monsterPoint[i];
        }
        arr[n+1]=monsterLength;
        int[][] dp=new int[n+2][n+2];
        for(int i=2;i<arr.length;i++){
            for(int j=0;j<=n+1-i;j++){
                dp[j][i+j]=Integer.MAX_VALUE;
                for(int k=j+1;k<j+i;k++){
                    dp[j][i+j]=Math.min(dp[j][i+j],
                                dp[j][k]+dp[k][i+j]+arr[i+j]-arr[j]);
                }
            }
        }
        return dp[0][n+1];
    }
}



牛妹是一家口罩厂家的老板，由于现在疫情严重，牛妹想重新分配每条生产线上的人数来使得能生产的口罩最多。
牛妹所在的公司一共有mm名员工，nn条生产线(0.....n-1)，每条生产线有strategy[i].size种人数安排策略。例如：33个人在aa生产线上，aa生产线每天生产88个口罩；55个人在aa生产线上，每天aa生产线能生产1515个口罩。
牛妹想知道通过合理的策略安排最多每天能生产多少口罩？（可以不用将所有员工都分配上岗，生产线可以选择闲置）

输入：
给定n,mn,m，strategystrategy数组
1 \leq n,m \leq 2*10^{3}1≤n,m≤2∗10 
3
 
strategy[i].size \ge 1 , \sum_{i=0}^{n-1}strategy[i].size \leq 3000strategy[i].size≥1,∑ 
i=0
n−1
​	
 strategy[i].size≤3000

1 \leq strategy[i][j].x \leq 2000,1 \leq strategy[i][j].y \leq 30001≤strategy[i][j].x≤2000,1≤strategy[i][j].y≤3000
strategy[i][j].x表示人数，strategy[i][j].y表示能生产的口罩数
输出：
返回每天最大的口罩生产数量



/*
 * public class Point {
 *   int x;
 *   int y;
 * }
 */

public class Solution {
    /**
     * 
     * @param n int整型 n
     * @param m int整型 m
     * @param strategy Point类二维数组 策略
     * @return int整型
     */
    public int producemask (int n, int m, Point[][] strategy) {
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                dp[i][j]=dp[i-1][j];
                for(Point p:strategy[i-1]){
                    if(j>=p.x)
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-p.x]+p.y);
                }
            }
        }
        return dp[n][m];
    }
}



