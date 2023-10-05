# Relate infrastructure`s TODO

インフラ関連のTODOを記載する．

# 方針

Domain

EntityのEmployeeは全てのフィールドがnonnull

## Ref

https://github.com/mybatis/mybatis-dynamic-sql/tree/master/src/test/kotlin/examples/kotlin/mybatis3/canonical
https://github.com/mybatis/mybatis-dynamic-sql
https://mybatis.org/mybatis-dynamic-sql/docs/kotlinMyBatis3.html
https://qiita.com/kazuki43zoo/items/9dfe35c47b08ce5f2ebb

# Entityの作成

- [x] Employee

# Mapperの作成

- [x] EmployeeMapper
    - [x] C: Single insert.
    - [x] R: Single select by pk.
    - [x] R: Multi select.
    - [x] U: Single update by pk.
    - [x] D: Single select by pk.

- [x] どこでExceptionを投げるか決める
    - update
        - 社員該当なし例外
            - これもリポジトリかな
    - それ以外は普通にありうる
        - Repository層かなで社員データソース例外

- [ ] リポジトリのテストが必要
