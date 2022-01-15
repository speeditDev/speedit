import * as React from 'react';
import styled from 'styled-components/native';

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

const AlertTabScreen = () => {
  return (
    <View>
      <Text>알림</Text>
    </View>
  );
};

export default AlertTabScreen;
