import * as React from 'react';
import styled from 'styled-components/native';
import {MyPageStackNavigationProps} from '../../../navigation/MyPageTabStackNavigator';

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
  padding-vertical: 10px;
  align-items: center;
  background-color: #f4a261;
  border-radius: 6px;
`;

const MyPageTabScreen = ({navigation}: MyPageStackNavigationProps<'MyPageTabScreen'>) => {
  return (
    <View>
      <Text>마이페이지</Text>
      <Button onPress={() => navigation.navigate('SettingScreen')}>
        <Text>설정</Text>
      </Button>
    </View>
  );
};

export default MyPageTabScreen;
