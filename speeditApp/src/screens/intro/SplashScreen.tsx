import React, {useEffect} from 'react';
import {Dimensions, Image, Text, View, ViewStyle} from 'react-native';
import Animated, {interpolate, useAnimatedStyle, useSharedValue, withTiming} from 'react-native-reanimated';
import {COLORS} from '../../styles/colors';
import {RootStackNavigationProps} from '../../navigation/RootStackNavigator';

const {width} = Dimensions.get('window');
const LOGO_SIZE = width * 0.65;

const logoResource = require('../../images/splash_logo_horizonal.png');

const SplashScreen = ({navigation}: RootStackNavigationProps<'SplashScreen'>) => {
  // const rotation = useSharedValue(0);
  const opacity = useSharedValue(0.1);

  const logoAnimation = useAnimatedStyle(() => {
    const rotate = interpolate(opacity.value, [0.1, 1], [0, -90]);
    return {
      // transform: [{rotateZ: `${rotation.value}deg`}],
      transform: [{rotateZ: `${rotate}deg`}],
      opacity: opacity.value,
    };
  });

  const goLogin = () => {
    // 로그인 화면으로
    navigation.reset({index: 0, routes: [{name: 'SnsLoginScreen'}]});
  };

  const goHome = () => {
    // 홈 화면으로
    navigation.reset({index: 0, routes: [{name: 'BottomGnbStackScreen'}]});
  };

  const checkToken = () => {
    // todo : 토큰 체크, 토큰 갱신 ?
    // todo : 자동 로그인
    const isValidToken = true;
    if (isValidToken) {
      goHome();
    } else {
      goLogin();
    }
  };

  useEffect(() => {
    // rotation.value = withTiming(-90, {duration: 1500});
    opacity.value = withTiming(1, {duration: 2000}); // 2초 동안 애니메이션
    setTimeout(() => checkToken(), 2200); // 2.2초뒤에 이동하도록
  }, []);

  return (
    <View style={{flex: 1, backgroundColor: COLORS.black, alignItems: 'center', justifyContent: 'center'}}>
      <Animated.View style={[logoAnimation]}>
        <Image source={logoResource} style={{width: LOGO_SIZE, height: LOGO_SIZE}} resizeMode={'contain'} />
      </Animated.View>
    </View>
  );
};

export default SplashScreen;
