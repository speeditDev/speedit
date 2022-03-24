import * as React from 'react';
import {useEffect, useState} from 'react';
import {SignUpStackNavigationProps} from '../../navigation/SignUpStackNavigator';
import {
  Alert,
  Keyboard,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import {COLORS} from '../../styles/colors';
import {spacing} from '../../styles/spacing';
import {Button} from '../../components/Button';
import {pretendard} from '../../styles/textStyled';
import Icons from '../../components/Icons';
import {useNavigation} from '@react-navigation/native';
import {SignUpStackNavigationType} from '../../navigation/SignUpStackNavigator';
import {Picker} from '@react-native-picker/picker';
import YearPicker from './YearPicker';
import {year as dataYear} from './YearData';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.black,
    paddingVertical: spacing.l,
    paddingLeft: spacing.m,
    paddingRight: spacing.l,
  },
});

const Container: React.FC = ({children}) => (
  <View style={styles.container}>
    <StatusBar barStyle={'light-content'} backgroundColor={COLORS.black} />
    {children}
  </View>
);

const GENDER: string[] = ['남성', '여성'];

const SignBirthScreen = ({route, navigation}: SignUpStackNavigationProps<'SignBirthScreen'>) => {
  const signNav = useNavigation<SignUpStackNavigationType>();

  const [yearModal, setYearModal] = useState(false);
  const [year, setYear] = useState<string>('');
  const [gender, setGender] = useState<string>('');

  const {nickname} = route.params;

  useEffect(() => {}, []);

  const onClickSelect = (userInfo: {nickname: string; year: string; index: number}) => {
    setGender(GENDER[userInfo.index]);
    signNav.navigate('SignJobScreen', {
      userName: userInfo.nickname,
      userYear: userInfo.year,
      userGender: GENDER[userInfo.index],
    });
  };

  return (
    <Container>
      <>
        <View
          style={{
            flexDirection: 'row',
            alignItems: 'center',
            paddingTop: spacing.blank_m,
            paddingBottom: spacing.l,
          }}>
          <Icons iconName={'user'} />
          <View
            style={{
              paddingLeft: spacing.xs,
            }}>
            <Text style={[pretendard.medium15, {color: COLORS.grey2}]}>{nickname}님에 대해 조금 더 알려주세요.</Text>
          </View>
        </View>
        <View
          style={{
            flexDirection: 'row',
            alignItems: 'center',
            paddingBottom: spacing.s,
            paddingRight: spacing.xs,
            marginBottom: spacing.blank_s,
            borderBottomWidth: 1.5,
            borderColor: COLORS.primaryWhite,
          }}>
          <View style={{flex: 1}}>
            <TouchableOpacity onPress={() => setYearModal(!yearModal)}>
              <TextInput
                multiline={false}
                numberOfLines={1}
                placeholder={'태어난 년도를 입력해주세요'}
                placeholderTextColor={COLORS.grey4}
                style={[pretendard.bold18, {color: COLORS.primaryWhite, textAlignVertical: 'bottom'}]}
                // returnKeyType={'search'}
                editable={false}
                value={year}
              />
            </TouchableOpacity>
            <YearPicker
              setModalOpen={setYearModal}
              modalOpen={yearModal}
              value={year}
              setValue={setYear}
              items={dataYear}
            />
          </View>
        </View>
        {year !== '' && !yearModal && (
          <View style={{flex: 1, flexDirection: 'row', flexWrap: 'wrap'}}>
            {GENDER.map((item, index) => (
              <View key={`category_${index}`} style={{flex: 1, marginRight: spacing.xs, marginBottom: spacing.s}}>
                <Button.Large
                  selected={false}
                  buttonTitle={item}
                  onPress={() => {
                    onClickSelect({nickname, year, index});
                  }}
                />
              </View>
            ))}
          </View>
        )}
      </>
    </Container>
  );
};

export default SignBirthScreen;
