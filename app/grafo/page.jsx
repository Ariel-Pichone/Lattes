'use client';

import { Button, Label, Modal, Select, TextInput } from 'flowbite-react';
import { useForm, useWatch } from 'react-hook-form';

export default function Grafo() {
  const { register, handleSubmit, getValues, control } = useForm();
  useWatch({
    control,
    name: 'value1',
  });

  useWatch({
    control,
    name: 'value2',
  });

  useWatch({
    control,
    name: 'value3',
  });

  useWatch({
    control,
    name: 'value4',
  });

  useWatch({
    control,
    name: 'value5',
  });

  // useWatch({
  //   control,
  //   name: 'input1',
  // });

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Grafo</h1>

        <form className='flex justify-between items-center"'>
          <div className="mr-4" id="select">
            <Select>
              <option value="">Instituto</option>
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select>
              <option value="">Pesquisador</option>
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select>
              <option value="">Tipo Prod.</option>
              <option>Artigo</option>
              <option>Livro</option>
            </Select>
          </div>

          <div className="mr-4" id="select">
            <Select>
              <option value="">Tipo Vértice</option>
              <option>Pesquisador</option>
              <option>Instituto</option>
            </Select>
          </div>

          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Aplicar
            </button>
          </div>
        </form>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg pt-5">
        <div className="">
          <form
            onSubmit={() => {}}
            className=" grid items-center gap-5 grid-cols-3"
          >
            <p className="text-2xl">Vértice (Cor)</p>
            <p className="text-2xl">Valor NP (início)</p>
            <p className="text-2xl">Valor NP (fim)</p>
            <div className="grid-rows-5">
              <Select>
                <option value="">Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Roxa</option>
                <option>Vermelha</option>
                <option>Azul</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Azul</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Verde</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Amarela</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Verde</option>
              </Select>
            </div>

            <div className="grid-rows-5 grid-cols-1">
              <TextInput disabled="true" value="1" {...register('input1')} />
              <TextInput
                disabled="true"
                value={
                  getValues('value1') && parseInt(getValues('value1'), 10) + 1
                }
                {...register('input2')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value2') && parseInt(getValues('value2'), 10) + 1
                }
                {...register('input3')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value3') && parseInt(getValues('value3'), 10) + 1
                }
                {...register('input4')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value4') && parseInt(getValues('value4'), 10) + 1
                }
                {...register('input5')}
              />
            </div>

            <div className="grid-rows-5">
              <TextInput
                type="number"
                {...register('value1')}
                placeholder="5"
              />

              {console.log(getValues('input1'))}

              <TextInput
                type="number"
                min={parseInt(getValues('input1'))}
                {...register('value2')}
                placeholder="8"
              />
              <TextInput
                type="number"
                {...register('value3')}
                placeholder="10"
              />
              <TextInput
                type="number"
                {...register('value4')}
                placeholder="15"
              />
              <TextInput
                type="number"
                {...register('value5')}
                placeholder="20"
              />
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
