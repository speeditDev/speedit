import * as React from 'react';
import styled from 'styled-components/native';
import {RootStackNavigationProps} from '../../navigation/RootStackNavigator';

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const Text = styled.Text`
  font-size: 16px;
  color: ${props => props.theme.textColor.main};
`;

const Button = styled.TouchableOpacity`
  width: 50%;
  margin-vertical: 10px;
  padding-vertical: 10px;
  align-items: center;
  background-color: #f4a261;
  border-radius: 6px;
`;

const SnsLoginScreen = ({navigation}: RootStackNavigationProps<'SnsLoginScreen'>) => {
  return (
    <View>
      <Text>간편로그인</Text>
      <Button onPress={() => navigation.navigate('SignUpScreen')}>
        <Text>로그인하기</Text>
      </Button>

      <Button onPress={() => navigation.navigate('OcrSampleStackScreen')}>
        <Text>Ocr 임시 테스트</Text>
      </Button>
    </View>
  );
};

export default SnsLoginScreen;
