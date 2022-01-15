import * as React from 'react';
import styled from 'styled-components/native';

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const Text = styled.Text``;

const SettingScreen = () => {
  return (
    <View>
      <Text>설정</Text>
    </View>
  );
};

export default SettingScreen;
