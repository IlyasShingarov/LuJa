function fibonacci(n : int) : int
    if n <= 0 then
        return 0
    elseif n == 1 then
        return 1
    else
        return fibonacci(n  - 1) + fibonacci(n - 2)
    end
end

for i = 0, 10 do
    print(fibonacci(i))
end


-- function fibonacci(n : int) : int
--     if n <= 0 then
--         return 0
--     elseif n == 1 then
--         return 1
--     else
--         local a = fibonacci(n - 1)
--         local b = fibonacci(n - 2)
--         local c = a + b
--         return c
--     end
-- end
--
-- for i = 0, 10 do
--     local a = fibonacci(i)
--     print(a)
-- end
--
--
