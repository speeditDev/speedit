import * as React from 'react';
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
import {RootStackNavigationType} from '../../navigation/RootStackNavigator';
import {useState} from 'react';

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

const JOB_CATEGORY: string[] = [
  'CEO/사업가',
  'PO/PM',
  'IT개발자',
  '건설/건축',
  '경영/사무',
  '공공/복지',
  '교육',
  '금융/투자',
  '기획/전략',
  '디자이너',
  '마케팅/광고',
  '무역/유통',
  '방송/미디어',
  '법조인',
  '상담/컨설팅',
  '서비스업',
  '영업/판매',
  '연구개발자',
  '예술인',
  '의료종사자',
  '인사/HR',
  '작가/에디터',
  '재무/회계',
  '크리에이터',
];

const SignJobScreen = ({route, navigation: {navigate}}: SignUpStackNavigationProps<'SignJobScreen'>) => {
  const rootNav = useNavigation<RootStackNavigationType>();

  const [jobs, setJobs] = useState({job: '', select: false});
  const [company, setCompany] = useState<string>('');

  const nickname: string = route.params.userName;
  const year: string = route.params.userYear;
  const gender: string = route.params.userGender;

  const onSignUp = () => {
    if (!jobs.select) {
      Alert.alert('직업을 선택해주세요');
      return;
    } else {
      // 회원정보 서버저장 후 화면전환
      rootNav.navigate('BottomGnbStackScreen');
    }
  };

  console.log('화면2', nickname, year, gender);
  console.log(jobs);
  console.log(jobs.job);
  console.log(jobs.select);

  const onSelect = item => {
    setJobs({job: item, select: !jobs.select});
  };

  return (
    <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
      <Container>
        <>
          <View
            style={{
              flexDirection: 'row',
              alignItems: 'center',
              paddingBottom: spacing.xxl,
            }}>
            <Icons iconName={'job'} />
            <View
              style={{
                paddingLeft: spacing.xs,
              }}>
              <Text style={[pretendard.medium15, {color: COLORS.grey2}]}>
                {nickname}
                {'님은 어떤 일을 하고 계신가요? '}
              </Text>
            </View>
          </View>

          <View style={{flex: 1, flexDirection: 'row', flexWrap: 'wrap'}}>
            {jobs.select ? (
              <>
                <View style={{marginRight: spacing.xs, marginBottom: spacing.s}}>
                  <Button.Medium selected={true} buttonTitle={jobs.job} onPress={() => onSelect('')} />
                </View>
                <View
                  style={{
                    flexDirection: 'row',
                    alignItems: 'center',
                    paddingTop: spacing.xs,
                    paddingBottom: spacing.s,
                    paddingRight: spacing.xs,
                    marginBottom: spacing.blank_s,
                    borderBottomWidth: 1.5,
                    borderColor: COLORS.primaryWhite,
                  }}>
                  <TextInput
                    multiline={false}
                    numberOfLines={1}
                    maxLength={30}
                    placeholder={'회사명, 또는 소속을 입력해주세요. (선택)'}
                    placeholderTextColor={COLORS.grey4}
                    style={[pretendard.bold18, {color: COLORS.primaryWhite, textAlignVertical: 'bottom'}]}
                    returnKeyType={'search'}
                    onChangeText={text => setCompany(text)}
                    value={company}
                  />
                </View>
              </>
            ) : (
              JOB_CATEGORY.map((item, index) => (
                <View key={`category_${index}`} style={{marginRight: spacing.xs, marginBottom: spacing.s}}>
                  <Button.Medium selected={jobs.select} buttonTitle={item} onPress={() => onSelect(item)} />
                </View>
              ))
            )}
          </View>

          <View
            style={{
              paddingBottom: spacing.s,
            }}>
            <Button selected={!jobs.select} buttonTitle={'가입 완료'} onPress={() => onSignUp()} />
          </View>
        </>
      </Container>
    </TouchableWithoutFeedback>
  );
};

export default SignJobScreen;
