剑指 Offer 04. 二维数组中的查找
在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:

现有矩阵 matrix 如下：

[
[1,   4,  7, 11, 15],
[2,   5,  8, 12, 19],
[3,   6,  9, 16, 22],
[10, 13, 14, 17, 24],
[18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

```java
//要从数组的右上角或者左下角看起
public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length < 1) return false;
        int y = matrix[0].length-1, x = 0, height = matrix.length , width = matrix[0].length;

        while (x < height && y >= 0) {
            if (target == matrix[x][y]) return true;
            if (matrix[x][y] > target)
                y--;
             else if (matrix[x][y] < target) x++;
        }

        return false;
    }
```
