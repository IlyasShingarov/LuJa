

local a = {3, 6, 2, 9};

function bubbleSort(arr: array, n: int) : void
    for i = 0, n-1 do
        for j = 0, n-i-2 do
            local a = arr[j];
            local b = arr[j+1]
            if a > b then
                local temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            end
        end
    end

    return;
end

print("Initial array: ");
for i = 0, 3 do
    print(a[i]);
end

bubbleSort(a, 4);

print("Sorted array: ");
for i = 0, 3 do
    print(a[i]);
end