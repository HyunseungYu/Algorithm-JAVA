/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
typedef long long ll;

typedef struct {
    int val;
    int idx;
} Pair;

int cmp(const void* a, const void* b){
    Pair* x = (Pair*)a;
    Pair* y = (Pair*)b;

    if(x->val != y->val)
        return x->val - y->val;

    return x->idx - y->idx;
}

long long* distance(int* nums, int N, int* returnSize) {
    *returnSize = N;
    long long* ans = (long long*) malloc(sizeof(long long) * N);

    Pair* arr = malloc(N * sizeof(Pair));

    for(int i=0; i<N; i++) {
        arr[i].val = nums[i];
        arr[i].idx = i;
    }

    qsort(arr, N, sizeof(Pair), cmp);
    ll* res = calloc(N, sizeof(ll));
    int i =0;
    while(i < N) {
        int j = i; // 처음 같아지는 지점
        while(j < N && arr[j].val == arr[i].val)
            j++;

        int c = j - i;
        if(1 < c) {
            ll sum = 0;
            for(int k=i; k<j; k++)
                sum += arr[k].idx;
            int prev = arr[i].idx;
            res[prev] = sum - (ll)c * prev;
            int left = 0, right = c-2;
            for(int k=i+1; k<j; k++) {
                int cur = arr[k].idx;
                res[cur] = res[prev] + (ll)(left - right) * (cur - prev);
                left++;
                right--;
                prev= cur;
            }
        }

        i=j;
    }

    free(arr);
    return res;
}