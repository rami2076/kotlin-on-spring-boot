# Employee service

## Logging

- [ ] KotlinでLoggingを簡単にする方法を実施する。
    - like lombok.Slf4j
- [ ] [Encode log in JSON.](https://github.com/logfellow/logstash-logback-encoder)

## GitHub Actions

- [x] CI/CDの内、mainブランチにPRするタイミングでテストを実施すること

## Code generation

- [x] 調査と適応
- [ ] 設定を全てgradleに記述

[Frontend and Backend](https://qiita.com/takahashik0422/items/0e6ca3ca18d76713222a)
[openapi-generator-sample](https://github.com/Msksgm/openapi-generator-sample/tree/main)

## lombok

- [ ] 調査と適応
  [For Kotlin](https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.lombok)

## database setting

- [x] 文字列の設定
- [x] 時間の設定

## Feature

### 要件

社員テーブルのCRUDのエンドポイント

### データソース

- [x] テーブルの作成
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
  - [x] 文字数
  - [x] 文字種
  - [x] ドキュメントにまとめる
- [x] GitHubTemplateを追加
  - [x] 動作確認
- [ ] openapiのyamlを設定して5つの正常形が動作するように変更する
- [ ] openapiでエラー判定のユースケースを設定する
- [ ] openapiでエラーハンドラーの設定を行う．もしくは自前のエラーハンドラを設定する．


