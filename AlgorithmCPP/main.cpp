#include <iostream>
#include <vector>

template<typename T>
void test(bool (*compare)(const std::vector<int> &vectore));

int main(int argc, char const *argv[])
{
    std::cout << "Hello world" << std::endl;
    return 0;
}
