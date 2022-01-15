import React, {useMemo} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import {Line, Response} from '../../util/mlkit';

interface ResponseRendererProps {
  response?: Response;
  scale: number;
}

export type Size = {
  width: number;
  height: number;
};

export const ResponseRenderer = ({response, scale}: ResponseRendererProps) => {
  return (
    <View
      style={{
        ...StyleSheet.absoluteFillObject,
        backgroundColor: 'transparent',
      }}>
      {response?.blocks.map(block => {
        return block.lines.map((line, index) => {
          return <BlockComponent line={line} scale={scale} key={`key${index}`} />;
        });
      })}
    </View>
  );
};

type BlockProps = {
  line: Line;
  scale: number;
};

export const BlockComponent = ({line, scale}: BlockProps) => {
  const rect = useMemo(() => {
    return {
      left: line.rect.left * scale,
      top: line.rect.top * scale,
      height: line.rect.height * scale,
      width: line.rect.width * scale,
    };
  }, [line, scale]);

  // console.warn('rect', rect, line.rect, scale);

  return (
    <View
      style={{
        position: 'absolute',
        ...rect,
        borderWidth: 1,
        borderColor: 'red',
      }}>
      <Text style={{color: 'blue'}}>{line.text}</Text>
    </View>
  );
};
