import * as React from 'react';
import {useEffect, useState} from 'react';
import {RootStackNavigationProps} from '../../navigation/RootStackNavigator';
import {
  Alert,
  Keyboard,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import {COLORS} from '../../styles/colors';
import {spacing} from '../../styles/spacing';
import {Button} from '../../components/Button';
import {pretendard} from '../../styles/textStyled';
import Icons from '../../components/Icons';
import {useNavigation, useRoute} from '@react-navigation/native';
import { SignUpStackNavigationProps, SignUpStackNavigationType } from "../../navigation/SignUpStackNavigator";

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
    <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
    {children}
  </View>
);

const SignNicknameScreen = ({route, navigation: {navigate}}: SignUpStackNavigationProps<'SignNicknameScreen'>) => {
  const signNav = useNavigation<SignUpStackNavigationType>();
  const [inputNickname, setInputNickname] = useState<string>(route.params.loginName);

  const onClickStart = (confirmNickname: string) => {
    signNav.navigate('SignBirthScreen', {nickname: confirmNickname});
  };

  const handleSubmit = () => {
    if (inputNickname.length < 1) {
      Alert.alert('닉네임을 입력해주세요');
      return;
    }
  };

  useEffect(() => {}, []);

  const handleErase = () => setInputNickname('');

  return (
    <Container>
      <>
        <View
          style={{
            flexDirection: 'row',
            alignItems: 'center',
            paddingTop: spacing.blank_m,
            paddingBottom: spacing.l,
          }}>
          <Icons iconName={'user'} />
          <View
            style={{
              paddingLeft: spacing.xs,
            }}>
            <Text style={[pretendard.medium15, {color: COLORS.grey2}]}>{'닉네임을 알려주세요. (최대 10글자)'}</Text>
          </View>
        </View>
        <View
          style={{
            flexDirection: 'row',
            alignItems: 'center',
            paddingBottom: spacing.s,
            paddingRight: spacing.xs,
            marginBottom: spacing.blank_s,
            borderBottomWidth: 1.5,
            borderColor: COLORS.primaryWhite,
          }}>
          <View style={{flex: 1}}>
            <TextInput
              multiline={false}
              numberOfLines={1}
              maxLength={30}
//              placeholder={route.params.name}
//               value={route.params.name}
              placeholder={'닉네임을 입력해주세요.'}
              placeholderTextColor={COLORS.grey4}
              style={[pretendard.bold18, {color: COLORS.primaryWhite, textAlignVertical: 'bottom'}]}
              returnKeyType={'search'}
              onChangeText={text => setInputNickname(text)}
              value={inputNickname}
              onSubmitEditing={handleSubmit}
            />
          </View>
          {inputNickname.length > 0 ? (
            <TouchableOpacity
              style={{width: 24, height: 24, alignItems: 'center', justifyContent: 'center'}}
              onPress={handleErase}>
              <Icons iconName={'erase'} />
            </TouchableOpacity>
          ) : null}
        </View>
        <Button buttonTitle={'시작하기'} onPress={() => onClickStart(inputNickname)}></Button>
      </>
    </Container>
  );
};

export default SignNicknameScreen;
