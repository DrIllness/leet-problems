package algorithms_playground


fun main() {
    println(search(intArrayOf(4,5,6,7,0,1,2), 0))
    println(search(intArrayOf(4,5,6,7,0,1,2), 3))
}

private fun search(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) return -1
    var low = 0
    var high = nums.size - 1

    while (low <= high) {
        val mid = (high - low / 2)
        if (target == nums[mid]) return mid

        if (nums[low] <= nums[mid]) {
            if (target < nums[mid] && target >= nums[low]) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        } else {
            if (target > nums[mid] && target <= nums[high]) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
    }

    return -1
}