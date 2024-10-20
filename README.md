# Several MockK examples
When interface(s) of o specific class you want to test is unknown (part of a bigger framework/black box) then a mock object can come to rescue. 
One popular mocking framework for Kotlin is MockK.

This is a small sample project showing the abilities of the MockK framework. The interesting parts take place in the test package where all mocking is performed.

The test case ```fun 'call original for specific param'()``` starts by mocking with a special function spyk, that is able to call both real functions (implemented) and mocked ones. For all inputs except 'leet' the mocked object will return hello, specifically for 'leet' input we will call the original function and get the mapped result back (real). In the end we verify which functions was called.
