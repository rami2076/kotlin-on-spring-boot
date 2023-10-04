# Integration EmployeeSearchController Get Test

# 方針

以下をテストする
20x系
50x系

# テストすること

- [ ] データベースに登録済み社員が2名登録されている場合，
    - [ ] /v1/employee/1で番号を指定してHTTPのGETメソッドでリクエストし成功した場合
        - [x] HTTPステータス200が返却されること
        - [x] クエリパラメータで指定した番号の社員が返却されること
        - [ ] 500系 DB接続エラー
        - 空の場合はテストしない

- [ ] データベースに登録済み社員が2名登録されている場合，
    - [ ] /v1/employee/をHTTPのGETメソッドでリクエストし成功した場合
        - [x] HTTPステータス200が返却されること
        - [x] 社員検索レスポンスが番号の昇順で返却されること
        - [ ] 500系 DB接続エラー
        - 空の場合はテストしない

- [ ] データベースに登録済み社員が2名登録されている場合，
    - [ ] /v1/employee/1をHTTPのDELETEメソッドでリクエストし成功した場合
        - [x] HTTPステータス204が返却されること
        - [ ] 削除されていること
        - [ ] 500系 DB接続エラー

- [ ] データベースに登録済み社員が2名登録されている場合，
    - [ ] /v1/employeeに社員更新リクエストをHTTPのPUTメソッドでリクエストし成功した場合
        - [x] HTTPステータス204が返却されること
        - [ ] 更新されていること
        - [ ] 500系 DB接続エラー

- [ ] /v1/employeeに社員登録リクエストをHTTPのPOSTメソッドでリクエストし成功した場合
    - [x] HTTPステータス201が返却されること
    - [ ] 登録されていること
    - [ ] 500系 DB接続エラー