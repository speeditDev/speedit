# SpeeditApp (가칭)

주의사항
- yarn 을 사용해주세요
- 안드로이드 스튜디오 설치 필요 ( with adb 설정 )
- WebStorm -> speeditApp 을 root 로 열어주세요
- WebStorm -> preferences -> eslint 비활성화
- WebStrom -> preferences -> prettier 활성화 ( auth format/save 체크 )

- 안드로이드 스튜디오 -> speeditApp/android 를 root 로 열어주세요
-
- xcode -> speeditApp/ios 를 루트로 열어주세요.
- 혹은 iox/~~~.xcworkspace 파일을 더블클릭

세팅
- styled-component ( 원하면 사용, from "styled-components/native" )
- react-navigation v6
- reanimated v2
- react-native-swiper

예정
- custom font 설정
- splash
- react-native-gesture-handler ( 필요하면 설치 및 세팅예정)

---- 

#### 안드로이드 네이티브 모듈 만들기
- https://reactnative.dev/docs/native-modules-android

----
#### iOS 네이티브 모듈 만들기
- https://reactnative.dev/docs/native-modules-ios
- https://reactnative.dev/docs/native-modules-ios#promises

----
### 참고:
- https://developers.google.com/ml-kit/vision/text-recognition/v2  
  (한글버전 지원은 v2(beta) 이상 )
- https://github.com/dimaportenko/react-native-mlkit-tutorial
----

## 카카오 로그인 설정 ( iOS )
- 카카오 설정 SDK: 활성화 ON
- 카카오 애플리케이션 만들고, 네이티브 키 활용
- 번들 ID 설정 ( XCode 값이랑 카카오에 등록하는 값이랑 동일하게 )
- 번들 ID : com.bside.speedit.bookplate
- https://github.com/react-native-seoul/react-native-kakao-login
- plist 설정
- https://developers.kakao.com/docs/latest/ko/reference/design-guide
- 에러났떤 부분 ( plist 에 키값없음, url scheme 콤마 제거안해서 )
- https://www.youtube.com/watch?v=uCn1xIijuos&list=PLMu8UG37vF6oJLNhjsjoy_ApcJFZZwJOo&index=12
-
## 카카오 로그인 설정 ( Android )
- 키 해시 등록 필요
- https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android#add-key-hash
- https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android
- React-Native 에서는 `android/app/debug.keystore` 의 해시를 추가해주시면 됩니다.
  --> 현재 버그가 있음 ( 막 등록해 놓음, 수정필요, 찾아야함, 개발자 모두 등록해야함, 맥, 윈도우, 디버그, 릴리즈 각각)
```
```
- https://www.youtube.com/watch?v=YJaOT3ZVKNg&t=15s
- 위에 해도 안될 시 ( 네이티브 카카오 로그인 모듈의 string 값에서 네이티브 키 값 수정하기  )

## 구글 로그인 설정 ( Android )
#### 1. google-signin 모듈 설치
- yarn add @react-native-google-signin/google-signin
#### 2. 안드로이드 환경 설정(NOT using Firebase)
- https://github.com/react-native-google-signin/google-signin/blob/master/docs/android-guide.md
- `android/app/build.gradle` 파일은 위의 내용 외에 아래와 같이 추가 수정 필요
```js
/* 삭제 */
apply plugin: 'com.google.gms.google-services'
/* 추가 in dependencies */
implementation 'com.google.android.gms:play-services-auth:20.0.0'
implementation "androidx.browser:browser:1.2.0"
```
#### 3. 사용자 인증을 위한 구글 API(OAuth 2.0) 설정
- (구글 공식 안내사이트) https://support.google.com/cloud/answer/6158849#installedapplications&android
- (참고사이트) https://dev.to/suyashvash/google-authsignin-in-react-native-without-firebase-43n
- 위의 `카카오 로그인`에서 생성한 키 해시(`android/app/debug.keystore`) 사용 시 아래코드로 SHA-1 키를 확인하여 구글 API(OAuth)에 등록
```
keytool -list -v -keystore android/app/debug.keystore
```
