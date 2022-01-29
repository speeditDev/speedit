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

## 카카오 로그인 설정
- 카카오 설정 SDK: 활성화 ON
- 카카오 애플리케이션 만들고, 네이티브 키 활용
- 번들 ID 설정 ( XCode 값이랑 카카오에 등록하는 값이랑 동일하게 )
- 번들 ID : com.bside.speedit.bookplate
- https://github.com/react-native-seoul/react-native-kakao-login
- plist 설정
- https://developers.kakao.com/docs/latest/ko/reference/design-guide
- 에러났떤 부분 ( plist 에 키값없음, url scheme 콤마 제거안해서 )
