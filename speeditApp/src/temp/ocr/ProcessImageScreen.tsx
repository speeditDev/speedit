import React, {useEffect, useState} from 'react';
import {Dimensions, ScrollView, Text} from 'react-native';
import {recognizeImage, Response} from '../../util/mlkit';

import {OcrSampleStackNavigationProp} from './OcrSampleStackNavigator';

const {width} = Dimensions.get('window');

export const ProcessImageScreen = ({route}: OcrSampleStackNavigationProp<'ProcessImageScreen'>) => {
  const [aspectRatio, setAspectRation] = useState(1);
  const [response, setResponse] = useState<Response | undefined>(undefined);
  const [fullRecognizeText, setFullRecognizeText] = useState<string>('');

  const uri = route.params.uri;

  useEffect(() => {
    if (uri) {
      proccessImage(uri);
    }
  }, [uri]);

  const proccessImage = async (url: string) => {
    if (url) {
      try {
        const response = await recognizeImage(url);
        // console.log(JSON.stringify(response));
        if (response?.blocks?.length > 0) {
          const concatText = response.blocks
            .map(block => block.lines.map(line => line.text))
            .reduce((wordA, wordB) => [...wordA, ...wordB])
            .reduce((a, b) => `${a}\n${b}`);

          console.log(concatText);
          setFullRecognizeText(concatText);
          setResponse(response);
          setAspectRation(response.height / response.width);
        }
      } catch (e) {
        console.log(e);
      }
    }
  };

  return (
    <ScrollView style={{flex: 1, paddingHorizontal: 8}}>
      <Text style={{fontSize: 14, includeFontPadding: false, lineHeight: 16}}>{fullRecognizeText}</Text>
      {/*<Image source={{ uri }} style={{ width, height: width * aspectRatio }} resizeMode="cover" />*/}
      {/*{!!response && <ResponseRenderer response={response} scale={width / response.width} />}*/}
    </ScrollView>
  );
};
