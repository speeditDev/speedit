import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import {RouteProp} from '@react-navigation/native';
import {SelectImageScreen} from './SelectImageScreen';
import {ProcessImageScreen} from './ProcessImageScreen';

type OcrSampleStackParam = {
  SelectImageScreen: undefined;
  ProcessImageScreen: {
    uri: string;
  };
};

const OcrSampleStack = createNativeStackNavigator<OcrSampleStackParam>();

export interface OcrSampleStackNavigationProp<RouteName extends keyof OcrSampleStackParam> {
  navigation: NativeStackNavigationProp<OcrSampleStackParam, RouteName>;
  route: RouteProp<OcrSampleStackParam, RouteName>;
}

const OcrSampleStackNavigator = () => (
  <OcrSampleStack.Navigator initialRouteName={'SelectImageScreen'}>
    <OcrSampleStack.Screen name={'SelectImageScreen'} component={SelectImageScreen} />
    <OcrSampleStack.Screen name={'ProcessImageScreen'} component={ProcessImageScreen} />
  </OcrSampleStack.Navigator>
);

export default OcrSampleStackNavigator;
