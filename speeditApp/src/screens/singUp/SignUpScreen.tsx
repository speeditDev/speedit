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
  padding-vertical: 10px;
  align-items: center;
  background-color: #f4a261;
  border-radius: 6px;
`;

const SnsLoginScreen = ({navigation}: RootStackNavigationProps<'SnsLoginScreen'>) => {
  return (
    <View>
      <Text>회원가입</Text>
      <Button
        onPress={() => {
          navigation.navigate('BottomGnbStackScreen');
          // navigation.reset({index: 0, routes: [{name: 'BottomGnbStackScreen'}]});
        }}>
        <Text>GNB가기</Text>
      </Button>
    </View>
  );
};

export default SnsLoginScreen;
