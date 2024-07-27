
local a = {1, {2, 3}, 4};

print(a[1]);
print(a[2][1]);

-- function test(a, b)
--     local sum = 0
--     for i = a, b do
--         print(sum)
--         print(i)
--         sum = sum + i
--     end
--     return sum
-- end
-- print(test(1, 10))
-- a = 10
-- b = 2.4
-- c = "Hello world"
-- -- d = b + a
--
-- local d = 10 + 2.5 + 13;
-- local e = d + 23 + a;
--
-- print(e);
-- -- local a = 10;
--
-- hi = 10;
--
-- meh = a + 10;
--
-- if a then
--
--     local b = 10;
--     if b then
--
--         b = 2;
--         local d = 10;
--
--     end
--
-- else
--
--     local c = 10;
--
-- end
--
-- local e = 10;


-- a = true
-- b = true - true
-- c = "Man"
-- d = "Bye"
-- a = 5 - 6;
-- b = 3.4 - 5.2;
-- c = 5.2 - 3;
-- d = 6 - 3.2;

-- local a = {3, 6, 2, 9};
--
-- function bubbleSort(arr: array, n: int) : void
--     for i = 0, n-1 do
--         for j = 0, n-i-2 do
--             local a = arr[j];
--             local b = arr[j+1]
--             if a > b then
--                 local temp = arr[j];
--                 arr[j] = arr[j+1];
--                 arr[j+1] = temp;
--             end
--         end
--     end
--
--     return;
-- end
--
-- print("Initial array: ");
-- for i = 0, 3 do
--     print(a[i]);
-- end
--
-- bubbleSort(a, 4);
--
-- print("Sorted array: ");
-- for i = 0, 3 do
--     print(a[i]);
-- end