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
  [Пример использования](https://asciinema.org/a/TsxEUumL953QN9WhO7iM7SKjG)
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
  [Пример использования](https://asciinema.org/a/L3mG2yp19djWOJHTqzdslEELE)
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
  [Пример использования](https://asciinema.org/a/uOe78RfFytGsNwOXBvJtZj0NL)
~~~
make json filePath1 filePath2
~~~
~~~json5
// JSON OUTPUT VIEW

{"chars1":{"status":"UNCHANGED","oldValue":["a","b","c"],"newValue":["a","b","c"]},"chars2":{"status":"UPDATED","oldValue":["d","e","f"],"newValue":false},"checked":{"status":"UPDATED","oldValue":false,"newValue":true},"default":{"status":"UPDATED","oldValue":null,"newValue":["value1","value2"]},"id":{"status":"UPDATED","oldValue":45,"newValue":null},"key1":{"status":"DELETED","oldValue":"value1","newValue":null},"key2":{"status":"ADDED","oldValue":null,"newValue":"value2"},"numbers1":{"status":"UNCHANGED","oldValue":[1,2,3,4],"newValue":[1,2,3,4]},"numbers2":{"status":"UPDATED","oldValue":[2,3,4,5],"newValue":[22,33,44,55]},"numbers3":{"status":"DELETED","oldValue":[3,4,5],"newValue":null},"numbers4":{"status":"ADDED","oldValue":null,"newValue":[4,5,6]},"obj1":{"status":"ADDED","oldValue":null,"newValue":{"nestedKey":"value","isNested":true}},"setting1":{"status":"UPDATED","oldValue":"Some value","newValue":"Another value"},"setting2":{"status":"UPDATED","oldValue":200,"newValue":300},"setting3":{"status":"UPDATED","oldValue":true,"newValue":"none"}}
~~~
