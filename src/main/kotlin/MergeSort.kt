fun main() {
    testMergeSort()
}

private fun testMergeSort() {
    val arr1 = intArrayOf(3, 4 ,5, 1)
    val arr2 = intArrayOf(3, 4 ,5, 1, 6, 2, 3, 8, 4, 8, 4)
    mergeSort(arr1)
    mergeSort(arr2)

    arr1.printArr()
    arr2.printArr()
}

private fun mergeSort(array: IntArray) {
    val swapArr = IntArray(array.size)
    sort(array, swapArr, 0, array.size - 1)
}

private fun merge(array: IntArray, swapArr: IntArray, low1: Int, high1: Int, low2: Int, high2: Int) {
    var i = low1
    var j = low2
    var swapIdx = low1
    while (i <= high1 && j <= high2) {
        if (array[i] < array[j]) {
            swapArr[swapIdx++] = array[i++]
        } else {
            swapArr[swapIdx++] = array[j++]
        }
    }

    while (i <= high1) swapArr[swapIdx++] = array[i++]
    while (j <= high2) swapArr[swapIdx++] = array[j++]

    swapIdx = low1
    while (swapIdx <= high2) {
        array[swapIdx] = swapArr[swapIdx]
        swapIdx++
    }
}

private fun sort(array: IntArray, swapArr: IntArray, start: Int, end: Int) {
    if (start >= end) return

    val mid = start + (end - start) / 2
    sort(array, swapArr, start, mid)
    sort(array, swapArr, mid + 1, end)

    merge(array, swapArr, start, mid, mid + 1, end)
}