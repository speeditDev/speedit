import React, {useState} from 'react';
import {Dimensions, Image, ScrollView, View} from 'react-native';
import * as ImagePicker from 'react-native-image-picker';
import {ImagePickerResponse} from 'react-native-image-picker/src/types';
import styled from 'styled-components/native';
import {OcrSampleStackNavigationProp} from './OcrSampleStackNavigator';

const {width} = Dimensions.get('window');

const Button = styled.Pressable`
  background-color: #fad2e1;
  width: 40%;
  padding-vertical: 20px;
  align-items: center;
  border-radius: 8px;
`;

const Text = styled.Text`
  font-size: 20px;
  font-weight: 500;
`;

const SafeAreaView = styled.SafeAreaView`
  flex: 1;
`;

const ButtonContainer = styled.View`
  flex-direction: row;
  padding: 10px;
  justify-content: space-between;
`;

export const SelectImageScreen = ({navigation}: OcrSampleStackNavigationProp<'SelectImageScreen'>) => {
  const [response, setResponse] = useState<ImagePickerResponse | null>(null);

  const onButtonPress = React.useCallback((type, options) => {
    if (type === 'capture') {
      ImagePicker.launchCamera(options, setResponse);
    } else {
      ImagePicker.launchImageLibrary(options, setResponse);
    }
  }, []);
  //
  // React.useEffect(() => {
  //   if (response) {
  //     navigation.navigate(routes.PROCESS_IMAGE_SCREEN, {
  //       uri: response?.assets?.[0]?.uri!!,
  //     });
  //   }
  // }, [response, navigation]);

  const onProcessImage = () => {
    if (response) {
      navigation.navigate('ProcessImageScreen', {
        uri: response?.assets?.[0]?.uri!!,
      });
    }
  };

  return (
    <SafeAreaView style={{flex: 1}}>
      <ScrollView>
        {response?.assets &&
          response?.assets.map(({uri}) => (
            <View key={uri} style={{alignItems: 'center'}}>
              <Image resizeMode="cover" resizeMethod="scale" style={{width, height: width}} source={{uri: uri}} />
            </View>
          ))}

        <View style={{paddingHorizontal: 8}}>
          <Text>{JSON.stringify(response, null, 2)}</Text>
        </View>

        <ButtonContainer>
          <Button
            onPress={() =>
              onButtonPress('capture', {
                saveToPhotos: false,
                mediaType: 'photo',
                includeBase64: false,
              })
            }>
            <Text>{`Take Image`}</Text>
          </Button>

          <Button
            onPress={() =>
              onButtonPress('library', {
                selectionLimit: 0,
                mediaType: 'photo',
                includeBase64: false,
              })
            }>
            <Text>{`Select Image`}</Text>
          </Button>
        </ButtonContainer>

        <Button onPress={onProcessImage}>
          <Text>{`Process Image`}</Text>
        </Button>
      </ScrollView>
    </SafeAreaView>
  );
};
