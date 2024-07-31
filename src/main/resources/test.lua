-- local list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
--
-- local a = #list
--
-- print(a)

function fibonacci(n)
    if n <= 1 then
        return n
    else
        return fibonacci(n - 1) + fibonacci(n - 2)
    end
end
print(fibonacci(20))

-- local list = {next = nil, value = 1}
--
-- local current = list
-- for i = 2, 10 do
--     local new = {next = nil, value = i}
--     current["next"] = new
--     current = current["next"]
-- end

-- current = list
-- while current ~= nil do
--     print(current["value"])
--     current = current["next"]
-- end
--
-- function invert_list(list)
--     local current = list
--     local prev = nil
--     local next = nil
--
--     while current ~= nil do
--         next = current["next"]
--         current["next"] = prev
--         prev = current
--         current = next
--     end
--
--     return prev
-- end
--
-- print("INVERTED")
-- local inverted = invert_list(list)
-- current = inverted
-- while current ~= nil do
--     print(current["value"])
--     current = current["next"]
-- end
