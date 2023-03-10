## Вычислитель отличий

[![Actions Status](https://github.com/av-starodub/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/av-starodub/java-project-71/actions)
[![Java CI](https://github.com/av-starodub/java-project-71/actions/workflows/javaci.yml/badge.svg)](https://github.com/av-starodub/java-project-71/actions/workflows/javaci.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/1bab56c13d765ad0e2a8/maintainability)](https://codeclimate.com/github/av-starodub/java-project-71/maintainability)
[![codecov](https://codecov.io/gh/av-starodub/java-project-71/branch/main/graph/badge.svg?token=XGDU7QTSQJ)](https://codecov.io/gh/av-starodub/java-project-71)

### Описание

Вычислитель отличий – программа, определяющая разницу между двумя структурами данных.

Возможности утилиты:
* Поддержка разных входных форматов: yaml и json
* Генерация отчета в виде plain text, stylish и json

Пример использования:
```# формат plain
./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# формат stylish
./app filepath1.json filepath2.json

{
+ follow: false
+ numbers: [1, 2, 3]
  setting1: Value 1
- setting2: 200
- setting3: true
+ setting3: {key=value}
+ setting4: blah blah
  }
```
* [Сравнение JSON файлов ( 'stylish' output format )](https://asciinema.org/a/mUhFoqS1xFXZI0F3QgIsR0eH1)
* [Сравнение YAML файлов ( 'plain' output format )](https://asciinema.org/a/LUmmorIb6Wc3LyNueXaNiijQ6)
* [Сравнение YAML файлов ( 'json' output format )](https://asciinema.org/a/oFseBpRAyiLB1GtUQgmKyt7sh)
