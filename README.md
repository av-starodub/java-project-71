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
~~~text
// STYLISH OUTPUT VIEW

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
~~~text
// PLAIN TEXT OUTPUT VIEW

Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
~~~
* ##### Генерация отчета в формате 'json'
  [Сравнение YAML файлов](https://asciinema.org/a/s7Imnp6UcAmasH3xwE7EJtn7k)
~~~
make json filePath1 filePath2
~~~
~~~json5
// JSON OUTPUT VIEW

{"chars1":{"STATUS":"UNCHANGED","OLD_VALUE":["a","b","c"]},"chars2":{"STATUS":"UPDATED","OLD_VALUE":["d","e","f"],"NEW_VALUE":false},"checked":{"STATUS":"UPDATED","OLD_VALUE":false,"NEW_VALUE":true},"default":{"STATUS":"UPDATED","OLD_VALUE":null,"NEW_VALUE":["value1","value2"]},"id":{"STATUS":"UPDATED","OLD_VALUE":45,"NEW_VALUE":null},"key1":{"STATUS":"DELETED","OLD_VALUE":"value1"},"key2":{"STATUS":"ADDED","NEW_VALUE":"value2"},"numbers1":{"STATUS":"UNCHANGED","OLD_VALUE":[1,2,3,4]},"numbers2":{"STATUS":"UPDATED","OLD_VALUE":[2,3,4,5],"NEW_VALUE":[22,33,44,55]},"numbers3":{"STATUS":"DELETED","OLD_VALUE":[3,4,5]},"numbers4":{"STATUS":"ADDED","NEW_VALUE":[4,5,6]},"obj1":{"STATUS":"ADDED","NEW_VALUE":{"nestedKey":"value","isNested":true}},"setting1":{"STATUS":"UPDATED","OLD_VALUE":"Some value","NEW_VALUE":"Another value"},"setting2":{"STATUS":"UPDATED","OLD_VALUE":200,"NEW_VALUE":300},"setting3":{"STATUS":"UPDATED","OLD_VALUE":true,"NEW_VALUE":"none"}}
~~~
