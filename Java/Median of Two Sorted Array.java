


GOOD RESOURCES: http://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/


/**
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        return (
            findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)
        ) / 2.0;
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start,
                              int[] B, int B_start,
                              int k){		
		if (A_start >= A.length) {
			return B[B_start + k - 1];
		}
		if (B_start >= B.length) {
			return A[A_start + k - 1];
		}

		if (k == 1) {
			return Math.min(A[A_start], B[B_start]);
		}
		
		int A_key = A_start + k / 2 - 1 < A.length
		            ? A[A_start + k / 2 - 1]
		            : Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.length
		            ? B[B_start + k / 2 - 1]
		            : Integer.MAX_VALUE; 
		
		if (A_key < B_key) {
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
		}
	}
}


Another binary implementation:

public static double findMedianSortedArrays(int A[], int B[]) {
	int m = A.length;
	int n = B.length;
 
	if ((m + n) % 2 != 0) // odd
		return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
	else { // even
		return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
			+ findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
	}
}
 
public static int findKth(int A[], int B[], int k, 
	int aStart, int aEnd, int bStart, int bEnd) {
 
	int aLen = aEnd - aStart + 1;
	int bLen = bEnd - bStart + 1;
 
	// Handle special cases
	if (aLen == 0)
		return B[bStart + k];
	if (bLen == 0)
		return A[aStart + k];
	if (k == 0)
		return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
 
	int aMid = aLen * k / (aLen + bLen); // a's middle count
	int bMid = k - aMid - 1; // b's middle count
 
	// make aMid and bMid to be array index
	aMid = aMid + aStart;
	bMid = bMid + bStart;
 
	if (A[aMid] > B[bMid]) {
		k = k - (bMid - bStart + 1);
		aEnd = aMid;
		bStart = bMid + 1;
	} else {
		k = k - (aMid - aStart + 1);
		bEnd = bMid;
		aStart = aMid + 1;
	}
 
	return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
}




FIND KTH SMALLEST ELEMENT:
ANOTHER VERSION:

m is array length, n is array b length
http://blog.csdn.net/feeltouch/article/details/45075661
int kthsmallest(int *a,int m,int *b,int n,int k) {
        if (m == 0) {
            return b[k - 1];
        }
        if (n == 0) {
            return a[k - 1];
        }
        if (k == 1) {
            return (a[0] < b[0])?a[0]:b[0];
        }
        if (k == m + n) {
            return (a[m - 1] > b[n - 1])?a[m - 1]:b[n - 1];
        }
        int i = ((double) m) / (m + n) * (k - 1);
        int j = k - 1 - i;
        if (j >= n) {
            j = n - 1;
            i = k - n;
        }
        if (((i == 0) || (a[i - 1] <= b[j])) && (b[j] <= a[i])) {
            return b[j];
        }
        if (((j == 0) || (b[j - 1] <= a[i])) && (a[i] <= b[j])) {
            return a[i];
        }
        if (a[i] <= b[j]) {
            return kthsmallest(a + i + 1, m - i - 1, b, j, k - i - 1);
        } else {
            return kthsmallest(a, i, b + j + 1, n - j - 1, k - j - 1);
        }

    }