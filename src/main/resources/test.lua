-- Make a linked list
local list = {next = nil, value = 1}

local current = list
for i = 2, 10 do
    local new = {next = nil, value = i}
    current["next"] = new
    current = current["next"]
end

-- Print the list
current = list
print(current)

while current ~= nil do
    print(current["value"])
    current = current["next"]
end

-- while 1 do
--     print("Hello, world!")
-- end