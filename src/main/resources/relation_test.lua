local e = 1 < 2; -- true
local f = 1 < 1; -- false
local g = 1 < 0; -- false

local h = 1 <= 2; -- true
local i = 1 <= 1; -- true
local j = 1 <= 0; -- false

local k = 1 > 2; -- false
local l = 1 > 1; -- false
local m = 1 > 0; -- true

local n = 1 >= 2; -- false
local o = 1 >= 1; -- true
local p = 1 >= 0; -- true

print(a);
print(b);
print(c);
print(d);
print(e);
print(f);
print(g);
print(h);
print(i);
print(j);
print(k);
print(l);
print(m);
print(n);
print(o);
print(p);