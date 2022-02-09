import * as React from 'react';
import {
  Dimensions,
  Image,
  StyleSheet,
  TouchableOpacity,
  View,
  Text,
  SafeAreaView,
  StatusBar,
  Platform,
} from 'react-native';
import {COLORS} from '../../styles/colors';
import {pretendard} from '../../styles/textStyled';
import {RootStackNavigationProps} from '../../navigation/RootStackNavigator';
import {spacing} from '../../styles/spacing';

import {
  KakaoOAuthToken,
  KakaoProfile,
  getProfile as getKakaoProfile,
  login,
  logout,
  unlink,
} from '@react-native-seoul/kakao-login';
import {
  GoogleSignin,
  GoogleSigninButton,
  NativeModuleError,
  statusCodes,
} from '@react-native-google-signin/google-signin';

const {width} = Dimensions.get('window');
const LOGO_SIZE = width * 0.65;

const logoResource = require('../../images/logo_vertical.png');

const TITLE_COMMENT = '문장 한 스푼으로 \n마음 양식 채우기, \n책그릇\n';
const GUIDE_COMMENT = 'sns로 간편하게 시작하기';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.black,
    paddingVertical: spacing.l,
    paddingLeft: spacing.m,
    paddingRight: spacing.l,
  },
});

const Container: React.FC = ({children}) => (
  <View style={styles.container}>
    <SafeAreaView style={{flex: 1}}>
      <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
      {children}
    </SafeAreaView>
  </View>
);

const SnsLoginScreen = ({navigation}: RootStackNavigationProps<'SnsLoginScreen'>) => {
  const isIOS = Platform.OS === 'ios' ? true : false;

  const onKakaoLogin = async () => {
    const kakaoToken = await login();
    const info = await getKakaoProfile();
    console.log(JSON.stringify(info));
    // info 안에 있는 정보들중 일부를 전역값으로 저장하고 있다가, 계정생성시 넘겨줘야함 ( 진석님 )
    navigation.navigate('SignUpScreen');
  };

  const onGoogleLogin = async () => {
    GoogleSignin.configure({
      offlineAccess: false,
    });

    const isSignedIn = await GoogleSignin.isSignedIn();
    if (isSignedIn) {
      await GoogleSignin.revokeAccess();
    }
    const info = await GoogleSignin.signIn();
    console.log(JSON.stringify(info));
    navigation.navigate('SignUpScreen');
  };

  return (
    <Container>
      <View style={{flex: 1, flexDirection: 'column', justifyContent: 'center', alignItems: 'center'}}>
        <View
          style={{
            flex: 1.5,
            justifyContent: 'flex-end',
            alignSelf: 'flex-start',
            paddingBottom: 15,
          }}>
          <Text style={[pretendard.extraBold28, {color: COLORS.whiteSentence}]}>{TITLE_COMMENT}</Text>
        </View>
        <View style={{flex: 1.8}}>
          <Image source={logoResource} style={{width: LOGO_SIZE, height: LOGO_SIZE}} resizeMode={'contain'} />
        </View>
        <View style={{flex: 0.5, justifyContent: 'flex-end', paddingBottom: 20}}>
          <Text style={[pretendard.medium14, {color: COLORS.grey3}]}>{GUIDE_COMMENT}</Text>
        </View>
        <View style={{flex: 0.8, flexDirection: 'row', justifyContent: 'center'}}>
          <View style={{paddingRight: 10}}>
            <TouchableOpacity onPress={() => onKakaoLogin()} style={{width: 52, height: 52, borderRadius: 26}}>
              <Image source={require('../../images/login_icon_kakao.png')} width={52} height={52} />
            </TouchableOpacity>
          </View>
          <View style={{paddingRight: 10}}>
            <TouchableOpacity onPress={() => {}} style={{width: 52, height: 52, borderRadius: 26}}>
              <Image source={require('../../images/login_icon_naver.png')} width={52} height={52} />
            </TouchableOpacity>
          </View>
          <View style={{paddingRight: 10}}>
            <TouchableOpacity onPress={() => onGoogleLogin()} style={{width: 52, height: 52, borderRadius: 26}}>
              <Image source={require('../../images/login_icon_goolge.png')} width={52} height={52} />
            </TouchableOpacity>
          </View>
          {isIOS && (
            <View>
              <TouchableOpacity onPress={() => {}} style={{width: 52, height: 52, borderRadius: 26}}>
                <Image source={require('../../images/login_icon_apple.png')} width={52} height={52} />
              </TouchableOpacity>
            </View>
          )}
          <View>
            <TouchableOpacity
              onPress={() => {
                navigation.reset({index: 0, routes: [{name: 'BottomGnbStackScreen'}]});
              }}
              style={{width: 52, height: 52, borderRadius: 10, backgroundColor: COLORS.primaryWhite}}>
              <Text style={[pretendard.bold18, {alignItems: 'center'}]}>Home{'\n'}이동</Text>
            </TouchableOpacity>
          </View>
          <View>
            <TouchableOpacity
              onPress={() => {
                navigation.reset({index: 0, routes: [{name: 'OcrSampleStackScreen'}]});
              }}
              style={{width: 52, height: 52, borderRadius: 10, backgroundColor: COLORS.primaryWhite}}>
              <Text style={[pretendard.bold18, {alignItems: 'center'}]}>OCR{'\n'}테스트</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </Container>
  );
};

export default SnsLoginScreen;
