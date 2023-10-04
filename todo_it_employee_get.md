# Integration EmployeeSearchController Get Test

# 方針

以下をテストする
20x系
50x系

# テストすること

- [ ] データベースに登録済み社員が2名登録されている場合，
    - [ ] /v1/employee/をHTTPのGETメソッドでリクエストし成功した場合
        - [x] HTTPステータス200が返却されること
        - [x] 社員検索レスポンスが番号の昇順で返却されること
        - [ ] 500系 DB接続エラー
        - 空の場合はテストしない

