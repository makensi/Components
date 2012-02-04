/*
	Copyright 2012 Raul de la Hoz Garrido

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package nc.components.exceptions;

/**
 * This exception represent any error produced by an attribute that not match
 * with expected kind. I.e: IntegerPickerView android:text should be an Integer
 * higher than 0
 * 
 * @author makensi
 * 
 */
public class InvalidAttributeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeException(String msg) {

	}
}
