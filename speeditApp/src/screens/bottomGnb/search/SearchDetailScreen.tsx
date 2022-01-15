import * as React from 'react';
import styled from 'styled-components/native';

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const Text = styled.Text``;

const SearchDetailScreen = () => {
  return (
    <View>
      <Text>도서 상세 정보</Text>
    </View>
  );
};

export default SearchDetailScreen;
