# Employee service

## Logging

- [ ] KotlinでLoggingを簡単にする方法を実施する。
    - like lombok.Slf4j
- [ ] [Encode log in JSON.](https://github.com/logfellow/logstash-logback-encoder)

## GitHub Actions

- [ ] CICDの内、mainブランチにPRするタイミングでテストを実施すること

## Code generation

- [x] 調査と適応

[Frontend and Backend](https://qiita.com/takahashik0422/items/0e6ca3ca18d76713222a)
[openapi-generator-sample](https://github.com/Msksgm/openapi-generator-sample/tree/main)

## lombok

- [ ] 調査と適応
  [For Kotlin](https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.lombok)

## lombok

- [ ] 調査と適応

## Feature

### 要件

社員テーブルのCRUDのエンドポイント

### データソース

- [ ] テーブルの作成
    - 社員テーブル作成
    - カラム
        - 番号（自動採番）
        - 氏名
        - 年齢
        - メールアドレス

### 機能一覧

- [ ] 全件取得
- [ ] 作成
    - [ ] 適宜バリデーション
- [ ] PK指定取得
- [ ] PK指定更新
    - [ ] 適宜バリデーション
- [ ] PK指定削除

#### その他の要件

JUnit5による単体テストも実装すること ※ここまでは時間的に難しいかも。余力があれば！

#### やること

- [ ] バリデーション要件決め

