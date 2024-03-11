## Вычислитель отличий

[![Actions Status](https://github.com/av-starodub/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/av-starodub/java-project-71/actions)
[![Java CI](https://github.com/av-starodub/java-project-71/actions/workflows/javaci.yml/badge.svg)](https://github.com/av-starodub/java-project-71/actions/workflows/javaci.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/1bab56c13d765ad0e2a8/maintainability)](https://codeclimate.com/github/av-starodub/java-project-71/maintainability)
[![codecov](https://codecov.io/gh/av-starodub/java-project-71/branch/main/graph/badge.svg?token=XGDU7QTSQJ)](https://codecov.io/gh/av-starodub/java-project-71)

### Описание

* Программа определяет разницу между двумя структурами данных
* Поддерживает входные форматы yaml и json

### Использование
~~~
git clone https://github.com/av-starodub/java-project-71.git
~~~
* #### Установка
~~~
cd java-project-71.git
~~~
~~~
make build
~~~
~~~
make install
~~~
* ##### Справка
~~~
make help
~~~
* ##### Генерация отчета в формате 'stylish' 
  [Сравнение JSON файлов](https://asciinema.org/a/TsxEUumL953QN9WhO7iM7SKjG)
~~~
make stylish filePath1 filePath2
~~~
~~~
STYLISH OUTPYT VIEW

{
+ follow: false
+ numbers: [1, 2, 3]
  setting1: Value 1
- setting2: 200
- setting3: true
+ setting3: {key=value}
+ setting4: blah blah
  }
~~~
* ##### Генерация отчета в формате 'plain text'
  [Сравнение YAML файлов](https://asciinema.org/a/L3mG2yp19djWOJHTqzdslEELE)
~~~
make plain filePath1 filePath2
~~~
~~~
PLAIN TEXT OUTPYT VIEW

Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
~~~
* ##### Генерация отчета в формате 'json'
  [Сравнение YAML файлов](https://asciinema.org/a/521xFoBVrMrEYYzGxltv1O0lW)
~~~
make json filePath1 filePath2
~~~
~~~
JSON OUTPYT VIEW

{
  "numbers2" : {
    "Status" : "UPDATED",
    "file1" : "[2, 3, 4, 5]",
    "file2" : "[22, 33, 44, 55]"
  },
  "numbers3" : {
    "Status" : "DELETED",
    "file1" : "[3, 4, 5]"
  },
  "numbers4" : {
    "Status" : "ADDED",
    "file2" : "[4, 5, 6]"
  },
  "obj1" : {
    "Status" : "ADDED",
    "file2" : "{nestedKey=value, isNested=true}"
  }
}
~~~
