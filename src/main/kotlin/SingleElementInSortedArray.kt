/**
 * 540. Single Element in a Sorted Array
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * */

fun main() {
    //println(singleNonDuplicate3(intArrayOf(1,1,2,3,3,4,4,8,8)))
    //println(singleNonDuplicate3(intArrayOf(3,3,7,7,10,11,11)))
    //println(singleNonDuplicate3(intArrayOf(1)))
    //println(singleNonDuplicate3(intArrayOf(3,3,5,7,7,8,8,11,11, 12, 12)))
    println(singleNonDuplicate3(intArrayOf(3,5, 5, 7,7,8,8,11,11, 12, 12)))
}

private fun singleNonDuplicate(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        var mid = (right + left) / 2

        val leftVal = if (mid > 0) {
            nums[mid - 1]
        } else {
            Int.MAX_VALUE
        }
        val rightVal = if (mid < nums.size - 1) {
            nums[mid + 1]
        } else {
            Int.MAX_VALUE
        }

        if (nums[mid] != leftVal && nums[mid] != rightVal)
            return nums[mid]
        else if (nums[mid] == nums[mid + 1]) {
            mid++ //inc by one so that we have twin-elements in one subarray
        }

        if ((mid - left + 1) % 2 == 0) { //checking if left subarray has even size ==> look into right
            left = mid + 1 // get out of twin-index
        } else {
            right = mid - 1 // get out of twin-index
        }

    }
    return -1
}

private fun singleNonDuplicate2(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        var mid = (right + left) / 2

        val leftVal = if (mid > 0) {
            nums[mid - 1]
        } else {
            nums[mid] + 1
        }
        val rightVal = if (mid < nums.size - 1) {
            nums[mid + 1]
        } else {
            nums[mid] + 1
        }

        if (nums[mid] != leftVal && nums[mid] != rightVal)
            return nums[mid]

        if (nums[mid] == nums[mid + 1]) {
            mid++ //inc by one so that we have twin-elements in one subarray
        }
        //3,3,7,7,8,8,11,11, 12, 12, 13
        if ((mid - left + 1) % 2 == 0) { //checking if left subarray has even size ==> look into right
            left = mid + 1 // get out of twin-index
        } else {
            right = mid - 1 // get out of twin-index
        }

    }
    return -1
}
// [ 3, 3, 7, 7, 8, 8, 11, 11, 12, 12, 13 ]
//   0, 1, 2, 3, 4, 5, 6,  7,  8,  9,  10
private fun singleNonDuplicate3(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1
    while (left < right) {
        var mid = (left + (right - left) / 2)
        if (mid % 2 == 1) {
            mid--
        }
        if (nums[mid] == nums[mid + 1]) {
            left = mid + 2 // jump over confirmed twin
        } else {
            right = mid
        }
    }

    return nums[left]
}