class Person:
    def init(self, name:str, address:str):
        self.__name = name
        self.__address = address
    def get_name(self) -> str:
        return self.__name

    def set_name(self, name:str) -> None:
        self.__name = name

    def get_address(self) -> str:
        return self.__address

    def set_address(self, address:str) -> None:
        self.__address = address
