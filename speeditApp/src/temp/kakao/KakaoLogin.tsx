import {
  KakaoOAuthToken,
  KakaoProfile,
  getProfile as getKakaoProfile,
  login,
  logout,
  unlink,
} from '@react-native-seoul/kakao-login';
import React, {useState} from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
import styled from 'styled-components/native';

const ButtonWrapper = styled.View`
  position: absolute;
  flex-direction: column;
  bottom: 40px;
  width: 85%;
  align-self: center;
`;

const KakaoLoginSample = () => {
  const [result, setResult] = useState<string>('');

  const signInWithKakao = async () => {
    const token = await login();
    console.log(JSON.stringify(token));
    setResult(JSON.stringify(token));
  };

  const signOutWithKakao = async (): Promise<void> => {
    const message = await logout();

    setResult(message);
  };

  const getProfile = async (): Promise<void> => {
    // @ts-ignore
    // const profile: KakaoProfile = await getKakaoProfile();

    const profile = await getKakaoProfile();
    setResult(JSON.stringify(profile));
  };

  const unlinkKakao = async (): Promise<void> => {
    const message = await unlink();

    setResult(message);
  };

  return (
    <View style={{flex: 1, alignContent: 'center', justifyContent: 'center'}}>
      <Text>{result}</Text>
      <ButtonWrapper>
        <TouchableOpacity
          testID="btn-login"
          style={{
            backgroundColor: '#FEE500',
            borderRadius: 40,
            borderWidth: 1,
            paddingVertical: 10,
          }}
          onPress={() => signInWithKakao()}>
          <Text style={{color: 'black', fontSize: 16}}>{`카카오 로그인`}</Text>
        </TouchableOpacity>

        <TouchableOpacity
          testID="btn-login"
          style={{
            backgroundColor: '#FEE500',
            borderRadius: 40,
            borderWidth: 1,
            paddingVertical: 10,
          }}
          onPress={() => getProfile()}>
          <Text style={{color: 'black', fontSize: 16}}>{`프로필 조회`}</Text>
        </TouchableOpacity>
        <TouchableOpacity
          testID="btn-login"
          style={{
            backgroundColor: '#FEE500',
            borderRadius: 40,
            borderWidth: 1,
            paddingVertical: 10,
          }}
          onPress={() => unlinkKakao()}>
          <Text style={{color: 'black', fontSize: 16}}>{`링크해제`}</Text>
        </TouchableOpacity>
        <View style={{marginTop: 12}} />
        <TouchableOpacity
          testID="btn-login"
          style={{
            backgroundColor: '#FEE500',
            borderRadius: 40,
            borderWidth: 1,
            paddingVertical: 10,
          }}
          onPress={() => signOutWithKakao()}>
          <Text style={{color: 'black', fontSize: 16}}>{`카카오 로그아웃`}</Text>
        </TouchableOpacity>
        <View style={{marginTop: 40}} />
      </ButtonWrapper>
    </View>
  );
};

export default KakaoLoginSample;
